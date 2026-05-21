package com.example.ecsite.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import com.example.ecsite.entity.Product;
import com.example.ecsite.entity.ProductImage;
import com.example.ecsite.form.ProductForm;
import com.example.ecsite.service.ProductService;

/**
 * 商品管理（管理者用）コントローラ。
 */
@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private static final long MAX_IMAGE_SIZE = 500 * 1024L;
    private static final List<String> ALLOWED_CONTENT_TYPES = List.of("image/jpeg", "image/png");
    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpeg", "jpg", "png");

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    /** 商品一覧画面を表示する（削除済み含む） */
    @GetMapping
    public String productList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/products/list";
    }

    /** 商品詳細画面を表示する */
    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId, Model model) {
        Product product = productService.findById(productId);
        if (product == null)
            return "redirect:/admin/products";
        List<ProductImage> images = productService.findImagesByProductId(productId);
        model.addAttribute("product", product);
        model.addAttribute("images", images);
        return "admin/products/detail";
    }

    /** 商品登録フォームを表示する */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("editMode", false);
        return "admin/products/form";
    }

    /**
     * 商品を新規登録する。
     * 商品名・価格・画像のバリデーション後にDBへ保存する。
     */
    @PostMapping("/new")
    public String createProduct(@Valid @ModelAttribute ProductForm form,
            BindingResult bindingResult,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            Model model,
            RedirectAttributes redirectAttributes) {
        String error = bindingResult.hasErrors()
                ? bindingResult.getAllErrors().get(0).getDefaultMessage()
                : validateImages(images);
        if (error != null) {
            model.addAttribute("productForm", form);
            model.addAttribute("editMode", false);
            model.addAttribute("errorMessage", error);
            return "admin/products/form";
        }
        try {
            productService.createProduct(form, images != null ? images : List.of());
            redirectAttributes.addFlashAttribute("successMessage", "商品を登録しました。");
            return "redirect:/admin/products";
        } catch (IOException e) {
            model.addAttribute("productForm", form);
            model.addAttribute("editMode", false);
            model.addAttribute("errorMessage", "画像の保存に失敗しました。");
            return "admin/products/form";
        }
    }

    /** 商品編集フォームを表示する */
    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable Long productId, Model model) {
        Product product = productService.findById(productId);
        if (product == null)
            return "redirect:/admin/products";
        List<ProductImage> images = productService.findImagesByProductId(productId);
        ProductForm form = new ProductForm();
        form.setName(product.getName());
        form.setDescription(product.getDescription());
        form.setPrice(product.getPrice().intValue());
        form.setDeletedFlag(product.isDeleted());
        model.addAttribute("productForm", form);
        model.addAttribute("product", product);
        model.addAttribute("images", images);
        model.addAttribute("editMode", true);
        return "admin/products/form";
    }

    /**
     * 商品を更新する。
     * 商品名・価格・画像のバリデーション後、既存画像の削除・新規画像追加が可能。合計最大5枚。
     */
    @PostMapping("/{productId}/edit")
    public String updateProduct(@PathVariable Long productId,
            @Valid @ModelAttribute ProductForm form,
            BindingResult bindingResult,
            @RequestParam(value = "images", required = false) List<MultipartFile> newImages,
            @RequestParam(value = "deleteImageIds", required = false) List<Long> deleteImageIds,
            Model model,
            RedirectAttributes redirectAttributes) {
        String error = null;
        if (bindingResult.hasErrors()) {
            error = bindingResult.getAllErrors().get(0).getDefaultMessage();
        } else {
            error = validateImages(newImages);
            if (error == null) {
                int currentCount = productService.countImages(productId);
                int deleteCount = (deleteImageIds != null) ? deleteImageIds.size() : 0;
                long addCount = (newImages != null) ? newImages.stream().filter(f -> !f.isEmpty()).count() : 0;
                if ((currentCount - deleteCount + addCount) > 5) {
                    error = "画像は最大5枚まで登録できます。";
                }
            }
        }
        if (error != null) {
            Product product = productService.findById(productId);
            List<ProductImage> images = productService.findImagesByProductId(productId);
            model.addAttribute("productForm", form);
            model.addAttribute("product", product);
            model.addAttribute("images", images);
            model.addAttribute("editMode", true);
            model.addAttribute("errorMessage", error);
            return "admin/products/form";
        }
        try {
            productService.updateProduct(productId, form, newImages, deleteImageIds);
            redirectAttributes.addFlashAttribute("successMessage", "商品を更新しました。");
            return "redirect:/admin/products/" + productId;
        } catch (IOException e) {
            model.addAttribute("errorMessage", "画像の保存に失敗しました。");
            return "redirect:/admin/products/" + productId + "/edit";
        }
    }

    /**
     * 画像ファイルを検証する。
     * エラーがあればエラーメッセージを返し、問題なければ null を返す。
     */
    private String validateImages(List<MultipartFile> images) {
        if (images == null)
            return null;
        for (MultipartFile img : images) {
            if (img.isEmpty())
                continue;
            if (img.getSize() > MAX_IMAGE_SIZE)
                return "画像ファイルのサイズは500KB以下にしてください";
            String contentType = img.getContentType();
            if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType))
                return "jpeg、pngのみアップロードできます";
            String filename = img.getOriginalFilename();
            if (filename != null) {
                String ext = filename.contains(".")
                        ? filename.substring(filename.lastIndexOf('.') + 1).toLowerCase()
                        : "";
                if (!ALLOWED_EXTENSIONS.contains(ext))
                    return "jpeg、pngのみアップロードできます";
            }
        }
        return null;
    }

    /** 商品削除確認画面を表示する */
    @GetMapping("/{productId}/delete")
    public String deleteConfirm(@PathVariable Long productId, Model model) {
        Product product = productService.findById(productId);
        if (product == null)
            return "redirect:/admin/products";
        model.addAttribute("product", product);
        return "admin/products/confirm-delete";
    }

    /**
     * 商品を論理削除する（deleted = true に設定）。
     */
    @PostMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId, RedirectAttributes redirectAttributes) {
        productService.softDelete(productId);
        redirectAttributes.addFlashAttribute("successMessage", "商品を削除しました。");
        return "redirect:/admin/products";
    }
}
