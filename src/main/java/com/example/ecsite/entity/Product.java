package com.example.ecsite.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
