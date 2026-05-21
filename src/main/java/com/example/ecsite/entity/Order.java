package com.example.ecsite.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Order {
    private Long orderId;
    private Long userId;
    private String orderNo;
    private String shipName;
    private String shipPostalCode;
    private String shipPrefecture;
    private String shipAddress1;
    private String shipAddress2;
    private String shipTelno;
    private int subtotalPrice;
    private int tax;
    private int totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getStatusDisplay() {
        return switch (status) {
            case "pending"  -> "未出荷";
            case "shipped"  -> "出荷済";
            case "canceled" -> "キャンセル";
            default         -> status;
        };
    }
}
