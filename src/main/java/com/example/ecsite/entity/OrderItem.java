package com.example.ecsite.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private int unitPrice;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
