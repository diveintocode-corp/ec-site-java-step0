package com.example.ecsite.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CartItem {
    private Long cartItemId;
    private Long cartId;
    private Long productId;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
