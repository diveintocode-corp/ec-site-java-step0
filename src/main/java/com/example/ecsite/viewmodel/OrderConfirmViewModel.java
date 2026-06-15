package com.example.ecsite.viewmodel;

import java.util.List;

import com.example.ecsite.entity.ShippingAddress;

import lombok.Data;

@Data
public class OrderConfirmViewModel {
    private ShippingSnapshot selectedAddress;
    private String address;
    private List<ShippingAddress> additionalAddresses;
    private List<OrderItemViewModel> orderItems;
    private int subtotal;
    private int tax;
    private int total;
    private int taxRate;
}
