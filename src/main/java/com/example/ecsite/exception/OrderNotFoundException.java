package com.example.ecsite.exception;

/**
 * 注文が見つからない場合にスローされる例外。
 * 存在しない注文 ID、またはアクセス権限がない注文にアクセスした場合に使用する。
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("注文が見つかりません: id=" + orderId);
    }
}
