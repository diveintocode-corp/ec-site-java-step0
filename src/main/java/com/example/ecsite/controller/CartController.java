package com.example.ecsite.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ecsite.security.CustomUserDetails;
import com.example.ecsite.service.CartService;
import com.example.ecsite.viewmodel.CartViewModel;

/**
 * カート（顧客用）コントローラ。
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * カート一覧画面を表示する。
     */
    @GetMapping
    public String cartList(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        CartViewModel cart = cartService.getCartViewModel(userDetails.getUserId());
        model.addAttribute("cart", cart);
        return "cart/list";
    }

    /**
     * カートに商品を追加する（AJAX エンドポイント）。
     * 既に同じ商品がある場合は数量を加算する。
     * レスポンス: {success, message, quantity}
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(
            @RequestParam Long productId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            int quantity = cartService.addToCart(userDetails.getUserId(), productId);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "カートに追加しました。",
                    "quantity", quantity));
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of(
                    "success", false,
                    "message", "カートへの追加に失敗しました。"));
        }
    }

    /**
     * カートからアイテムを削除する。
     * 自分のカートのアイテムのみ削除可能。
     */
    @PostMapping("/items/{cartItemId}/delete")
    public String deleteCartItem(@PathVariable Long cartItemId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        cartService.deleteCartItem(cartItemId, userDetails.getUserId());
        return "redirect:/cart";
    }
}
