package com.example.ecsite.viewmodel;

import lombok.Data;

/** カートアイテム表示用 ViewModel */
@Data
public class CartItemViewModel {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private String imageUrl;
    private int quantity;
    private int priceEx;
    private int priceIn;
}
