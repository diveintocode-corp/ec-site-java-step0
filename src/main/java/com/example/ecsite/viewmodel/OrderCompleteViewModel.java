package com.example.ecsite.viewmodel;

import java.util.List;

import com.example.ecsite.entity.Order;

import lombok.Data;

/** 注文完了画面用 ViewModel */
@Data
public class OrderCompleteViewModel {
    private Order order;
    private String address;
    private List<OrderItemViewModel> orderItems;
    private int taxRate;
}
