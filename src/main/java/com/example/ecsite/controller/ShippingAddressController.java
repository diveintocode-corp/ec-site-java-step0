package com.example.ecsite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecsite.entity.ShippingAddress;
import com.example.ecsite.form.ShippingAddressForm;
import com.example.ecsite.security.CustomUserDetails;
import com.example.ecsite.service.ShippingAddressService;

@Controller
@RequestMapping("/shipping-addresses")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    public ShippingAddressController(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @GetMapping
    public String list(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("shippingAddresses",
                shippingAddressService.findByUserId(userDetails.getUserId()));
        return "shipping-address/list";
    }

    @GetMapping("/new")
    public String newForm(@RequestParam(required = false) String from, Model model) {
        model.addAttribute("shippingAddressForm", new ShippingAddressForm());
        model.addAttribute("prefectures", ProfileController.PREFECTURE_LIST);
        model.addAttribute("action", "/shipping-addresses/new");
        model.addAttribute("from", from);
        model.addAttribute("pageTitle", "配送先を追加");
        return "shipping-address/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute ShippingAddressForm form,
                         @RequestParam(required = false) String from,
                         @AuthenticationPrincipal CustomUserDetails userDetails,
                         Model model) {
        String msg = form.validate();
        if (msg != null) {
            model.addAttribute("errorMessage", msg);
            model.addAttribute("shippingAddressForm", form);
            model.addAttribute("prefectures", ProfileController.PREFECTURE_LIST);
            model.addAttribute("action", "/shipping-addresses/new");
            model.addAttribute("from", from);
            model.addAttribute("pageTitle", "配送先を追加");
            return "shipping-address/form";
        }
        shippingAddressService.create(userDetails.getUserId(), form);
        if ("checkout".equals(from)) {
            return "redirect:/order/confirm";
        }
        return "redirect:/shipping-addresses";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id,
                           @AuthenticationPrincipal CustomUserDetails userDetails,
                           Model model) {
        ShippingAddress addr = shippingAddressService.findOwnedById(id, userDetails.getUserId());
        if (addr == null) {
            return "redirect:/shipping-addresses";
        }
        ShippingAddressForm form = new ShippingAddressForm();
        form.setName(addr.getName());
        form.setPostalCode(addr.getPostalCode());
        form.setPrefecture(addr.getPrefecture());
        form.setAddress1(addr.getAddress1());
        form.setAddress2(addr.getAddress2());
        form.setTelno(addr.getTelno());
        model.addAttribute("shippingAddressForm", form);
        model.addAttribute("prefectures", ProfileController.PREFECTURE_LIST);
        model.addAttribute("action", "/shipping-addresses/" + id + "/edit");
        model.addAttribute("from", null);
        model.addAttribute("pageTitle", "配送先を編集");
        return "shipping-address/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @ModelAttribute ShippingAddressForm form,
                         @AuthenticationPrincipal CustomUserDetails userDetails,
                         Model model) {
        String msg = form.validate();
        if (msg != null) {
            model.addAttribute("errorMessage", msg);
            model.addAttribute("shippingAddressForm", form);
            model.addAttribute("prefectures", ProfileController.PREFECTURE_LIST);
            model.addAttribute("action", "/shipping-addresses/" + id + "/edit");
            model.addAttribute("from", null);
            model.addAttribute("pageTitle", "配送先を編集");
            return "shipping-address/form";
        }
        shippingAddressService.update(id, userDetails.getUserId(), form);
        return "redirect:/shipping-addresses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        shippingAddressService.delete(id, userDetails.getUserId());
        return "redirect:/shipping-addresses";
    }
}
