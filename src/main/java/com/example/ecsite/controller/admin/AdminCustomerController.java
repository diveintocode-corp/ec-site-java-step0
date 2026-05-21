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
import com.example.ecsite.entity.Profile;
import com.example.ecsite.entity.User;
import com.example.ecsite.form.CustomerEditForm;
import com.example.ecsite.service.OrderService;
import com.example.ecsite.service.ProfileService;
import com.example.ecsite.service.UserService;

/**
 * 顧客管理（管理者用）コントローラ。
 */
@Controller
@RequestMapping("/admin/customers")
public class AdminCustomerController {

    private final UserService userService;
    private final ProfileService profileService;
    private final OrderService orderService;

    public AdminCustomerController(UserService userService,
            ProfileService profileService,
            OrderService orderService) {
        this.userService = userService;
        this.profileService = profileService;
        this.orderService = orderService;
    }

    /** 顧客一覧画面を表示する（管理者・スタッフ以外のユーザー） */
    @GetMapping
    public String customerList(Model model) {
        List<User> customers = userService.findAllCustomers();
        model.addAttribute("customers", customers);
        return "admin/customers/list";
    }

    /**
     * 顧客詳細画面を表示する。
     * プロフィール情報と注文履歴（出荷済み合計金額含む）を表示する。
     */
    @GetMapping("/{userId}")
    public String customerDetail(@PathVariable Long userId, Model model) {
        User customer = userService.findById(userId);
        if (customer == null)
            return "redirect:/admin/customers";
        Profile profile = profileService.findByUserId(userId);
        List<Order> orders = orderService.findAll().stream()
                .filter(o -> o.getUserId().equals(userId)).toList();
        int totalShipped = orderService.getTotalShipped(userId);
        model.addAttribute("customer", customer);
        model.addAttribute("profile", profile);
        model.addAttribute("orders", orders);
        model.addAttribute("totalShipped", totalShipped);
        return "admin/customers/detail";
    }

    /** 顧客編集フォームを表示する */
    @GetMapping("/{userId}/edit")
    public String editForm(@PathVariable Long userId, Model model) {
        User customer = userService.findById(userId);
        if (customer == null)
            return "redirect:/admin/customers";
        Profile profile = profileService.findByUserId(userId);
        CustomerEditForm form = new CustomerEditForm();
        form.setActive(customer.isActive());
        if (profile != null) {
            form.setName(profile.getName());
            form.setPostalCode(profile.getPostalCode());
            form.setPrefecture(profile.getPrefecture());
            form.setAddress1(profile.getAddress1());
            form.setAddress2(profile.getAddress2());
            form.setTelno(profile.getTelno());
        }
        model.addAttribute("customerEditForm", form);
        model.addAttribute("customer", customer);
        model.addAttribute("profile", profile);
        model.addAttribute("prefectures", com.example.ecsite.controller.ProfileController.PREFECTURE_LIST);
        return "admin/customers/form";
    }

    /**
     * 顧客情報を更新する。
     * アカウントの有効/無効とプロフィール情報を編集可能。
     */
    @PostMapping("/{userId}/edit")
    public String updateCustomer(@PathVariable Long userId,
            @ModelAttribute CustomerEditForm form,
            RedirectAttributes redirectAttributes) {
        User customer = userService.findById(userId);
        if (customer == null)
            return "redirect:/admin/customers";
        customer.setActive(form.isActive());
        userService.updateUser(customer);

        Profile profile = profileService.findByUserId(userId);
        if (profile != null) {
            profile.setName(form.getName());
            profile.setPostalCode(form.getPostalCode());
            profile.setPrefecture(form.getPrefecture());
            profile.setAddress1(form.getAddress1());
            profile.setAddress2(form.getAddress2());
            profile.setTelno(form.getTelno());
            profileService.updateProfile(profile);
        }
        redirectAttributes.addFlashAttribute("successMessage", "顧客情報を更新しました。");
        return "redirect:/admin/customers/" + userId;
    }
}
