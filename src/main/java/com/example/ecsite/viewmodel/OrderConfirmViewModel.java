package com.example.ecsite.viewmodel;

import java.util.List;

import com.example.ecsite.entity.Profile;

import lombok.Data;

/** 注文確認画面用 ViewModel */
@Data
public class OrderConfirmViewModel {
    private Profile profile;
    private String address;
    private List<OrderItemViewModel> orderItems;
    private int subtotal;
    private int tax;
    private int total;
    private int taxRate;
}
