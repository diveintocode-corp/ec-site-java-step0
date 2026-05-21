package com.example.ecsite.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {
    private Long userId;
    private String email;
    private String password;
    private boolean active;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
