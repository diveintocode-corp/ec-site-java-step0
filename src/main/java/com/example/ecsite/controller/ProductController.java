package com.example.ecsite.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecsite.service.ProductService;
import com.example.ecsite.viewmodel.ProductDetailViewModel;
import com.example.ecsite.viewmodel.ProductViewModel;


/**
 * 商品一覧・詳細（顧客用）コントローラ。
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 商品一覧画面を表示する。
     * 削除済み商品を除いた全商品を表示する。
     */
    @GetMapping
    public String productList(Model model) {
        List<ProductViewModel> products = productService.getActiveProductViewModels();
        for (ProductViewModel p : products) {
            String desc = p.getDescription() == null ? "" : p.getDescription().length() > 128 ? p.getDescription().substring(0, 128) : p.getDescription();
            p.setDescription(desc);
            p.setPriceIn(p.getPriceEx().multiply(BigDecimal.valueOf(1.0 + productService.getTaxRate())).intValue());
        }
        model.addAttribute("products", products);
        return "products/list";
    }

    /**
     * 商品詳細画面を表示する。
     * 削除済みまたは存在しない商品の場合は 404 を返す。
     */
    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId, Model model) {
        ProductDetailViewModel product = productService.getActiveProductDetailViewModel(productId);
        product.setPriceIn(product.getPriceEx().multiply(BigDecimal.valueOf(1.0 + productService.getTaxRate())).intValue());
        model.addAttribute("product", product);
        return "products/detail";
    }
}
