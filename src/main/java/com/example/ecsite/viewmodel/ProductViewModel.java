package com.example.ecsite.viewmodel;

import java.math.BigDecimal;

import lombok.Data;

/** 商品一覧画面用 ViewModel */
@Data
public class ProductViewModel {
    private Long productId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal priceEx;
    private int priceIn;
}
