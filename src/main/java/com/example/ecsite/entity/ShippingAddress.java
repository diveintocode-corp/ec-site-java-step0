package com.example.ecsite.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShippingAddress {
    private Long shippingAddressId;
    private Long userId;
    private String name;
    private String postalCode;
    private String prefecture;
    private String address1;
    private String address2;
    private String telno;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
