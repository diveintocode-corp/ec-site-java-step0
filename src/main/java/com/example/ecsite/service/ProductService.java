package com.example.ecsite.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecsite.entity.Product;
import com.example.ecsite.entity.ProductImage;
import com.example.ecsite.exception.ProductNotFoundException;
import com.example.ecsite.form.ProductForm;
import com.example.ecsite.repository.ProductImageRepository;
import com.example.ecsite.repository.ProductRepository;
import com.example.ecsite.viewmodel.ProductDetailViewModel;
import com.example.ecsite.viewmodel.ProductViewModel;

/**
 * 商品関連のビジネスロジックを提供するサービスクラス。
 */
@Service
public class ProductService {

    private static final int DESCRIPTION_MAX_LENGTH = 128;

    @Value("${app.tax-rate:0.1}")
    private double taxRate;

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ImageService imageService;

    public ProductService(ProductRepository productRepository,
            ProductImageRepository productImageRepository,
            ImageService imageService) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.imageService = imageService;
    }

    /**
     * 税込価格を計算する。
     */
    public int calcPriceIn(BigDecimal priceEx) {
        return priceEx.multiply(BigDecimal.valueOf(1.0 + taxRate)).intValue();
    }

    /**
     * 商品一覧（削除済みを除く）を画面表示用に取得する。
     */
    public List<ProductViewModel> getActiveProductViewModels() {
        List<Product> products = productRepository.findAllActive();
        return products.stream().map(p -> {
            List<ProductImage> images = productImageRepository.findByProductId(p.getProductId());
            String imageUrl = images.isEmpty() ? null : images.get(0).getImagePath();
            String desc = p.getDescription() == null ? ""
                    : p.getDescription().length() > DESCRIPTION_MAX_LENGTH
                            ? p.getDescription().substring(0, DESCRIPTION_MAX_LENGTH)
                            : p.getDescription();
            ProductViewModel vm = new ProductViewModel();
            vm.setProductId(p.getProductId());
            vm.setName(p.getName());
            vm.setDescription(desc);
            vm.setImageUrl(imageUrl);
            vm.setPriceEx(p.getPrice());
            vm.setPriceIn(calcPriceIn(p.getPrice()));
            return vm;
        }).toList();
    }

    /**
     * 商品詳細（削除済みを除く）を画面表示用に取得する。
     */
    public ProductDetailViewModel getActiveProductDetailViewModel(Long productId) {
        Product product = productRepository.findActiveById(productId);
        if (product == null)
            throw new ProductNotFoundException(productId);
        List<ProductImage> images = productImageRepository.findByProductId(productId);
        ProductDetailViewModel vm = new ProductDetailViewModel();
        vm.setProductId(product.getProductId());
        vm.setName(product.getName());
        vm.setDescription(product.getDescription());
        vm.setImages(images);
        vm.setPriceEx(product.getPrice());
        vm.setPriceIn(calcPriceIn(product.getPrice()));
        return vm;
    }

    /**
     * 全商品一覧（削除済み含む）を取得する（管理者用）。
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * 商品をIDで取得する（管理者用）。
     */
    public Product findById(Long productId) {
        return productRepository.findById(productId);
    }

    /**
     * 商品を新規登録する（管理者用）。
     */
    @Transactional
    public Product createProduct(ProductForm form, List<MultipartFile> images) throws IOException {
        Product product = new Product();
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(BigDecimal.valueOf(form.getPrice()));
        product.setDeleted(false);
        productRepository.insert(product);

        for (MultipartFile img : images) {
            if (!img.isEmpty()) {
                String path = imageService.saveImage(img, "products");
                ProductImage pi = new ProductImage();
                pi.setProductId(product.getProductId());
                pi.setImagePath(path);
                productImageRepository.insert(pi);
            }
        }
        return product;
    }

    /**
     * 商品を更新する（管理者用）。
     */
    @Transactional
    public void updateProduct(Long productId, ProductForm form,
            List<MultipartFile> newImages,
            List<Long> deleteImageIds) throws IOException {
        Product product = productRepository.findById(productId);
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(BigDecimal.valueOf(form.getPrice()));
        product.setDeleted(form.isDeletedFlag());
        productRepository.update(product);

        // 画像削除
        if (deleteImageIds != null) {
            for (Long imgId : deleteImageIds) {
                ProductImage img = productImageRepository.findById(imgId);
                if (img != null) {
                    imageService.deleteImage(img.getImagePath());
                    productImageRepository.deleteById(imgId);
                }
            }
        }

        // 画像追加
        if (newImages != null) {
            int currentCount = productImageRepository.countByProductId(productId);
            int deletedCount = (deleteImageIds != null) ? deleteImageIds.size() : 0;
            int remaining = currentCount - deletedCount;
            for (MultipartFile img : newImages) {
                if (!img.isEmpty() && remaining < 5) {
                    String path = imageService.saveImage(img, "products");
                    ProductImage pi = new ProductImage();
                    pi.setProductId(productId);
                    pi.setImagePath(path);
                    productImageRepository.insert(pi);
                    remaining++;
                }
            }
        }
    }

    /**
     * 商品を論理削除する（管理者用）。
     */
    @Transactional
    public void softDelete(Long productId) {
        productRepository.softDelete(productId);
    }

    /**
     * 商品の画像一覧を取得する。
     */
    public List<ProductImage> findImagesByProductId(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    /**
     * 商品画像の枚数をカウントする。
     */
    public int countImages(Long productId) {
        return productImageRepository.countByProductId(productId);
    }

    public double getTaxRate() {
        return taxRate;
    }
}
