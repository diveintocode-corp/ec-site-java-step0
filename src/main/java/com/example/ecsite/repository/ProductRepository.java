package com.example.ecsite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.Product;
import com.example.ecsite.mapper.ProductMapper;

/**
 * 商品データへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class ProductRepository {

    private final ProductMapper productMapper;

    public ProductRepository(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> findAllActive() {
        return productMapper.findAllActive();
    }

    public List<Product> findAll() {
        return productMapper.findAll();
    }

    public Product findActiveById(Long productId) {
        return productMapper.findActiveById(productId);
    }

    public Product findById(Long productId) {
        return productMapper.findById(productId);
    }

    public void insert(Product product) {
        productMapper.insert(product);
    }

    public void update(Product product) {
        productMapper.update(product);
    }

    public void softDelete(Long productId) {
        productMapper.softDelete(productId);
    }
}
