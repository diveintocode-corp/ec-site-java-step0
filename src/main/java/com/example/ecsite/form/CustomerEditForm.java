package com.example.ecsite.form;

import lombok.Data;

/**
 * 顧客アカウント編集フォーム（管理者用）。
 * フィールド: isActive（有効/無効）
 * ※ email は読み取り専用のため、フォームには含めない。
 */
@Data
public class CustomerEditForm {
    private boolean active;
    private String name;
    private String postalCode;
    private String prefecture;
    private String address1;
    private String address2;
    private String telno;
}
