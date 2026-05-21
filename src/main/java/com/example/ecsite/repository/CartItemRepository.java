package com.example.ecsite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.CartItem;
import com.example.ecsite.mapper.CartItemMapper;

/**
 * カートアイテムデータへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class CartItemRepository {

    private final CartItemMapper cartItemMapper;

    public CartItemRepository(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public List<CartItem> findByCartId(Long cartId) {
        return cartItemMapper.findByCartId(cartId);
    }

    public CartItem findById(Long id) {
        return cartItemMapper.findById(id);
    }

    public CartItem findByCartIdAndProductId(Long cartId, Long productId) {
        return cartItemMapper.findByCartIdAndProductId(cartId, productId);
    }

    public void insert(CartItem cartItem) {
        cartItemMapper.insert(cartItem);
    }

    public void update(CartItem cartItem) {
        cartItemMapper.update(cartItem);
    }

    public void deleteById(Long id) {
        cartItemMapper.deleteById(id);
    }

    public void deleteByCartId(Long cartId) {
        cartItemMapper.deleteByCartId(cartId);
    }
}
