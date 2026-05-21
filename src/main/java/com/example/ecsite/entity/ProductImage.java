package com.example.ecsite.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductImage {
    private Long productImageId;
    private Long productId;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
