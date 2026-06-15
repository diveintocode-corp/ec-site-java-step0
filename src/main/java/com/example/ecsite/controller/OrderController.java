package com.example.ecsite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.entity.ShippingAddress;
import com.example.ecsite.security.CustomUserDetails;
import com.example.ecsite.service.CartService;
import com.example.ecsite.service.OrderService;
import com.example.ecsite.service.ProfileService;
import com.example.ecsite.service.ShippingAddressService;
import com.example.ecsite.viewmodel.OrderCompleteViewModel;
import com.example.ecsite.viewmodel.OrderConfirmViewModel;
import com.example.ecsite.viewmodel.ShippingSnapshot;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ProfileService profileService;
    private final CartService cartService;
    private final ShippingAddressService shippingAddressService;

    public OrderController(OrderService orderService,
            ProfileService profileService,
            CartService cartService,
            ShippingAddressService shippingAddressService) {
        this.orderService = orderService;
        this.profileService = profileService;
        this.cartService = cartService;
        this.shippingAddressService = shippingAddressService;
    }

    @GetMapping("/confirm")
    public String orderConfirm(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Profile profile = profileService.findByUserId(userDetails.getUserId());
        if (profile == null) {
            return "redirect:/profile/edit";
        }
        ShippingSnapshot snapshot = ShippingSnapshot.of(profile);
        OrderConfirmViewModel vm = orderService.getOrderConfirmViewModel(userDetails.getUserId(), snapshot);
        vm.setAdditionalAddresses(shippingAddressService.findByUserId(userDetails.getUserId()));
        model.addAttribute("confirmData", vm);
        return "order/confirm";
    }

    @PostMapping("/complete")
    public String orderComplete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                @RequestParam(defaultValue = "profile") String selectedAddress,
                                Model model) {
        ShippingSnapshot snapshot;
        if ("profile".equals(selectedAddress)) {
            Profile profile = profileService.findByUserId(userDetails.getUserId());
            if (profile == null) {
                return "redirect:/profile/edit";
            }
            snapshot = ShippingSnapshot.of(profile);
        } else {
            try {
                Long addrId = Long.parseLong(selectedAddress);
                ShippingAddress addr = shippingAddressService.findOwnedById(addrId, userDetails.getUserId());
                if (addr == null) {
                    return "redirect:/order/confirm";
                }
                snapshot = ShippingSnapshot.of(addr);
            } catch (NumberFormatException e) {
                return "redirect:/order/confirm";
            }
        }

        if (cartService.getCartItems(userDetails.getUserId()).isEmpty()) {
            return "redirect:/cart";
        }

        OrderCompleteViewModel vm = orderService.completeOrder(userDetails.getUserId(), snapshot);
        if (vm != null) {
            model.addAttribute("completeData", vm);
            return "order/complete";
        } else {
            return "redirect:/cart";
        }
    }
}
