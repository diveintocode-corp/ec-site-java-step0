package com.example.ecsite.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecsite.entity.Cart;
import com.example.ecsite.entity.CartItem;
import com.example.ecsite.entity.Product;
import com.example.ecsite.entity.ProductImage;
import com.example.ecsite.repository.CartItemRepository;
import com.example.ecsite.repository.CartRepository;
import com.example.ecsite.repository.ProductImageRepository;
import com.example.ecsite.repository.ProductRepository;
import com.example.ecsite.viewmodel.CartItemViewModel;
import com.example.ecsite.viewmodel.CartViewModel;

/**
 * カート関連のビジネスロジックを提供するサービスクラス。
 */
@Service
public class CartService {

    @Value("${app.tax-rate:0.1}")
    private double taxRate;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
            ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    /**
     * ユーザーのカートを取得、存在しなければ作成する。
     */
    @Transactional
    public Cart getOrCreateCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartRepository.insert(cart);
        }
        return cart;
    }

    /**
     * カートの内容を画面表示用に取得する。
     */
    public CartViewModel getCartViewModel(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        List<CartItemViewModel> result = new ArrayList<>();
        int totalEx = 0;
        int totalIn = 0;

        if (cart != null) {
            List<CartItem> list = cartItemRepository.findByCartId(cart.getCartId());
            for (CartItem ci : list) {
                Product p = productRepository.findById(ci.getProductId());
                if (p == null)
                    continue;
                List<ProductImage> images = productImageRepository.findByProductId(p.getProductId());
                String url = images.isEmpty() ? null : images.get(0).getImagePath();

                CartItemViewModel vm = new CartItemViewModel();
                vm.setCartItemId(ci.getCartItemId());
                vm.setProductId(p.getProductId());
                vm.setProductName(p.getName());
                vm.setImageUrl(url);
                vm.setQuantity(ci.getQuantity());
                vm.setPriceEx(p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())).intValue());
                vm.setPriceIn(p.getPrice().multiply(BigDecimal.valueOf(1.0 + taxRate)).multiply(BigDecimal.valueOf(ci.getQuantity())).intValue());
                result.add(vm);

                totalEx += p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())).intValue();
                totalIn += p.getPrice().multiply(BigDecimal.valueOf(1.0 + taxRate)).multiply(BigDecimal.valueOf(ci.getQuantity())).intValue();
            }
        }

        CartViewModel vm = new CartViewModel();
        vm.setItems(result);
        vm.setTotalEx(totalEx);
        vm.setTotalIn(totalIn);
        vm.setTaxRate((int) (taxRate * 100));
        return vm;
    }

    /**
     * カートに商品を追加する。既に存在する場合は数量を加算する。
     *
     * @return 追加後の数量
     */
    @Transactional
    public int addToCart(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);
        CartItem existing = cartItemRepository.findByCartIdAndProductId(cart.getCartId(), productId);
        if (existing == null) {
            if (cart.getCartId() != null) {
                CartItem newItem = new CartItem();
                newItem.setCartId(cart.getCartId());
                newItem.setProductId(productId);
                newItem.setQuantity(1);
                cartItemRepository.insert(newItem);
            }
            return 1;
        } else {
            existing.setQuantity(existing.getQuantity() + 1);
            cartItemRepository.update(existing);
            return existing.getQuantity();
        }
    }

    /**
     * カートアイテムを削除する（所有権確認あり）。
     *
     * @return 削除成功なら true
     */
    @Transactional
    public boolean deleteCartItem(Long cartItemId, Long userId) {
        CartItem item = cartItemRepository.findById(cartItemId);
        Cart cart = cartRepository.findByUserId(userId);
        if (item != null) {
            if (cart != null) {
                if (cart.getCartId().equals(item.getCartId())) {
                    cartItemRepository.deleteById(cartItemId);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * カートの全アイテムを削除する（注文完了後）。
     */
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cartItemRepository.deleteByCartId(cart.getCartId());
        }
    }

    /**
     * カートのアイテム一覧を取得する。
     */
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null)
            return List.of();
        return cartItemRepository.findByCartId(cart.getCartId());
    }

    public double getTaxRate() {
        return taxRate;
    }
}
