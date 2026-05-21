package com.example.ecsite.viewmodel;

import java.math.BigDecimal;
import java.util.List;

import com.example.ecsite.entity.ProductImage;

import lombok.Data;

/** 商品詳細画面用 ViewModel */
@Data
public class ProductDetailViewModel {
    private Long productId;
    private String name;
    private String description;
    private List<ProductImage> images;
    private BigDecimal priceEx;
    private int priceIn;
}
