package com.example.ecsite.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Cart {
    private Long cartId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
