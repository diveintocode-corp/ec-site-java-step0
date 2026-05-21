package com.example.ecsite.viewmodel;

import lombok.Data;

/** 注文アイテム表示用 ViewModel */
@Data
public class OrderItemViewModel {
    private String productName;
    private String imageUrl;
    private int quantity;
    private int unitPrice;
    private int priceIn;
}
