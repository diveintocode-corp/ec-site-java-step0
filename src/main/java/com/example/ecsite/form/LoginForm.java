package com.example.ecsite.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * ログインフォーム。
 * フィールド: email, password
 */
@Data
public class LoginForm {
    @NotBlank
    @Email
    private String email;
    private String password;
}
