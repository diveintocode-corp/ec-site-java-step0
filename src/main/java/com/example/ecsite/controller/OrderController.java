package com.example.ecsite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.security.CustomUserDetails;
import com.example.ecsite.service.CartService;
import com.example.ecsite.service.OrderService;
import com.example.ecsite.service.ProfileService;
import com.example.ecsite.viewmodel.OrderCompleteViewModel;
import com.example.ecsite.viewmodel.OrderConfirmViewModel;

/**
 * 注文確認・完了（顧客用）コントローラ。
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ProfileService profileService;
    private final CartService cartService;

    public OrderController(OrderService orderService,
            ProfileService profileService,
            CartService cartService) {
        this.orderService = orderService;
        this.profileService = profileService;
        this.cartService = cartService;
    }

    /**
     * 注文確認画面を表示する。
     * プロフィール未登録の場合はプロフィール編集画面にリダイレクト。
     */
    @GetMapping("/confirm")
    public String orderConfirm(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Profile profile = profileService.findByUserId(userDetails.getUserId());
        if (profile == null) {
            return "redirect:/profile/edit";
        }
        OrderConfirmViewModel vm = orderService.getOrderConfirmViewModel(userDetails.getUserId(), profile);
        model.addAttribute("confirmData", vm);
        return "order/confirm";
    }

    /**
     * 注文を確定する（POST のみ）。
     * プロフィール未登録・カートが空の場合はリダイレクト。
     * 成功時は注文完了画面を表示する。
     */
    @PostMapping("/complete")
    public String orderComplete(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Profile profile = profileService.findByUserId(userDetails.getUserId());
        if (profile == null) {
            return "redirect:/profile/edit";
        }
        if (cartService.getCartItems(userDetails.getUserId()).isEmpty()) {
            return "redirect:/cart";
        }
        OrderCompleteViewModel vm = orderService.completeOrder(userDetails.getUserId(), profile);
        if (vm == null) {
            return "redirect:/cart";
        }
        model.addAttribute("completeData", vm);
        return "order/complete";
    }
}
