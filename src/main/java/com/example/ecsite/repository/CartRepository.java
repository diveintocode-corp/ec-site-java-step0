package com.example.ecsite.repository;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.Cart;
import com.example.ecsite.mapper.CartMapper;

/**
 * カートデータへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class CartRepository {

    private final CartMapper cartMapper;

    public CartRepository(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public Cart findByUserId(Long userId) {
        return cartMapper.findByUserId(userId);
    }

    public void insert(Cart cart) {
        cartMapper.insert(cart);
    }
}
