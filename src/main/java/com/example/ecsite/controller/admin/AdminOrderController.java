package com.example.ecsite.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecsite.entity.Order;
import com.example.ecsite.form.OrderEditForm;
import com.example.ecsite.service.OrderService;
import com.example.ecsite.viewmodel.OrderItemViewModel;

/**
 * 注文管理（管理者用）コントローラ。
 */
@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /** 注文一覧画面を表示する（新しい順） */
    @GetMapping
    public String orderList(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/orders/list";
    }

    /** 注文詳細画面を表示する */
    @GetMapping("/{orderId}")
    public String orderDetail(@PathVariable Long orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) return "redirect:/admin/orders";
        List<OrderItemViewModel> orderItems = orderService.findOrderItemViewModels(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        return "admin/orders/detail";
    }

    /** 注文編集フォームを表示する */
    @GetMapping("/{orderId}/edit")
    public String editForm(@PathVariable Long orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) return "redirect:/admin/orders";
        OrderEditForm form = new OrderEditForm();
        form.setShipName(order.getShipName());
        form.setShipPostalCode(order.getShipPostalCode());
        form.setShipPrefecture(order.getShipPrefecture());
        form.setShipAddress1(order.getShipAddress1());
        form.setShipAddress2(order.getShipAddress2());
        form.setShipTelno(order.getShipTelno());
        form.setStatus(order.getStatus());
        model.addAttribute("orderEditForm", form);
        model.addAttribute("order", order);
        return "admin/orders/form";
    }

    /**
     * 注文を更新する（配送先・ステータス変更）。
     */
    @PostMapping("/{orderId}/edit")
    public String updateOrder(@PathVariable Long orderId,
                               @ModelAttribute OrderEditForm form,
                               RedirectAttributes redirectAttributes) {
        Order order = orderService.findById(orderId);
        if (order == null) return "redirect:/admin/orders";
        order.setShipName(form.getShipName());
        order.setShipPostalCode(form.getShipPostalCode());
        order.setShipPrefecture(form.getShipPrefecture());
        order.setShipAddress1(form.getShipAddress1());
        order.setShipAddress2(form.getShipAddress2());
        order.setShipTelno(form.getShipTelno());
        order.setStatus(form.getStatus());
        orderService.updateOrder(order);
        redirectAttributes.addFlashAttribute("successMessage", "注文情報を更新しました。");
        return "redirect:/admin/orders/" + orderId;
    }
}
