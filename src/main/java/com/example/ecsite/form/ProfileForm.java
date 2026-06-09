package com.example.ecsite.form;

import lombok.Data;

/**
 * ユーザープロフィールフォーム。
 * フィールド: name, postalCode, prefecture, address1, address2, telno
 */
@Data
public class ProfileForm {
    private String name;
    private String postalCode;
    private String prefecture;
    private String address1;
    private String address2;
    private String telno;

    public String validate() {
        if (name != null && !name.isBlank()) {
            if (name.length() <= 64) {
                if (postalCode != null && !postalCode.isBlank()) {
                    if (postalCode.matches("\\d{7}")) {
                        if (prefecture != null && !prefecture.isBlank()) {
                            if (address1 != null && !address1.isBlank()) {
                                if (address1.length() <= 128) {
                                    if (address2 == null || address2.length() <= 128) {
                                        if (telno != null && !telno.isBlank()) {
                                            if (telno.matches("\\d{1,11}")) {
                                                return null;
                                            } else {
                                                return "電話番号は数字11桁以内で入力してください";
                                            }
                                        } else {
                                            return "電話番号は数字11桁以内で入力してください";
                                        }
                                    } else {
                                        return "建物名・部屋番号は128文字以内で入力してください";
                                    }
                                } else {
                                    return "市区町村・番地は128文字以内で入力してください";
                                }
                            } else {
                                return "市区町村・番地は128文字以内で入力してください";
                            }
                        } else {
                            return "都道府県を選択してください。";
                        }
                    } else {
                        return "郵便番号は数字7桁で入力してください";
                    }
                } else {
                    return "郵便番号は数字7桁で入力してください";
                }
            } else {
                return "氏名は64文字以内で入力してください";
            }
        } else {
            return "氏名を入力してください";
        }
    }
}
