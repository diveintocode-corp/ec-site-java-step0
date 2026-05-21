package com.example.ecsite.exception;

/**
 * 商品が見つからない場合にスローされる例外。
 * 削除済み、または存在しない商品 ID にアクセスした場合に使用する。
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("商品が見つかりません: id=" + productId);
    }
}
