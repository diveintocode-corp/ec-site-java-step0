package com.example.ecsite.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Profile {
    private Long profileId;
    private Long userId;
    private String name;
    private String postalCode;
    private String prefecture;
    /** address_1 カラムを address1 フィールドにマッピング */
    private String address1;
    /** address_2 カラムを address2 フィールドにマッピング */
    private String address2;
    private String telno;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
