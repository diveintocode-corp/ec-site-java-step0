package com.example.ecsite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.ProductImage;
import com.example.ecsite.mapper.ProductImageMapper;

/**
 * 商品画像データへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class ProductImageRepository {

    private final ProductImageMapper productImageMapper;

    public ProductImageRepository(ProductImageMapper productImageMapper) {
        this.productImageMapper = productImageMapper;
    }

    public List<ProductImage> findByProductId(Long productId) {
        return productImageMapper.findByProductId(productId);
    }

    public ProductImage findById(Long id) {
        return productImageMapper.findById(id);
    }

    public void insert(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    public void deleteById(Long id) {
        productImageMapper.deleteById(id);
    }

    public int countByProductId(Long productId) {
        return productImageMapper.countByProductId(productId);
    }
}
