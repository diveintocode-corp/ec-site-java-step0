package com.example.ecsite.form;

import lombok.Data;

/**
 * 注文編集フォーム（管理者用）。
 * フィールド: 配送先情報、注文ステータス
 */
@Data
public class OrderEditForm {
    private String shipName;
    private String shipPostalCode;
    private String shipPrefecture;
    private String shipAddress1;
    private String shipAddress2;
    private String shipTelno;
    private String status;
}
