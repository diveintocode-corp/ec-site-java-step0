package com.example.ecsite.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 商品登録・編集フォーム（管理者用）。
 * フィールド: name, description, price, deletedFlag
 * 画像は MultipartFile としてコントローラで別途受け取る。
 */
@Data
public class ProductForm {
    @NotBlank(message = "商品名は128文字以内で入力してください")
    @Size(max = 128, message = "商品名は128文字以内で入力してください")
    private String name;
    private String description;
    @Min(value = 1, message = "価格は1〜99,999,999の範囲で入力してください")
    @Max(value = 99_999_999, message = "価格は1〜99,999,999の範囲で入力してください")
    private int price;
    /** 商品が無効（論理削除）かどうか。false=販売中、true=停止中 */
    private boolean deletedFlag;
}
