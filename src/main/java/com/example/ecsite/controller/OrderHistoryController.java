package com.example.ecsite.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecsite.security.CustomUserDetails;
import com.example.ecsite.service.OrderService;
import com.example.ecsite.viewmodel.OrderHistoryItemViewModel;

/**
 * 注文履歴・キャンセル（顧客用）コントローラ。
 */
@Controller
@RequestMapping("/order-history")
public class OrderHistoryController {

    private final OrderService orderService;

    public OrderHistoryController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 注文履歴画面を表示する（新しい順）。
     */
    @GetMapping
    public String orderHistory(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<OrderHistoryItemViewModel> orderList = orderService.getOrderHistory(userDetails.getUserId());
        model.addAttribute("orderList", orderList);
        return "order-history/list";
    }

    /**
     * 注文をキャンセルする（POST のみ）。
     * ステータスが pending の場合のみキャンセル可能。
     * 自分の注文のみキャンセル可能。
     */
    @PostMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        orderService.cancelOrder(orderId, userDetails.getUserId());
        return "redirect:/order-history";
    }
}
