package com.example.ecsite.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザープロフィールフォーム。
 * フィールド: name, postalCode, prefecture, address1, address2, telno
 */
@Data
public class ProfileForm {
    @NotBlank(message = "氏名を入力してください")
    @Size(max = 64, message = "氏名は64文字以内で入力してください")
    private String name;
    @NotBlank(message = "郵便番号は数字7桁で入力してください")
    @Pattern(regexp = "\\d{7}", message = "郵便番号は数字7桁で入力してください")
    private String postalCode;
    @NotBlank(message = "都道府県を選択してください。")
    private String prefecture;
    @NotBlank(message = "市区町村・番地は128文字以内で入力してください")
    @Size(max = 128, message = "市区町村・番地は128文字以内で入力してください")
    private String address1;
    @Size(max = 128, message = "建物名・部屋番号は128文字以内で入力してください")
    private String address2;
    @NotBlank(message = "電話番号は数字11桁以内で入力してください")
    @Pattern(regexp = "\\d{1,11}", message = "電話番号は数字11桁以内で入力してください")
    private String telno;
}
