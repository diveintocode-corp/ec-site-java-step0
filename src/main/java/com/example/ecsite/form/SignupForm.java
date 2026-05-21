package com.example.ecsite.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザー登録フォーム。
 * フィールド: email, password, confirmPassword
 */
@Data
public class SignupForm {
    @NotBlank
    @Email
    private String email;
    @Size(min = 8, message = "パスワードは8文字以上で指定してください。")
    private String password;
    private String confirmPassword;
}
