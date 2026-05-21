package com.example.ecsite.controller.admin;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import com.example.ecsite.entity.User;
import com.example.ecsite.form.LoginForm;
import com.example.ecsite.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 管理者ログインコントローラ。
 */
@Controller
@RequestMapping("/admin")
public class AdminAccountController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public AdminAccountController(UserRepository userRepository,
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    /** 管理者ログイン画面を表示する */
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "admin/login";
    }

    /**
     * 管理者ログイン処理
     * - メールアドレスの形式チェック
     * - 登録済み・管理者ロールチェック
     * - 認証処理（パスワード照合）
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (bindingResult.hasFieldErrors("email")) {
            model.addAttribute("emailError", "メールアドレスの形式が異なっています");
            model.addAttribute("loginForm", form);
            return "admin/login";
        }

        User user = userRepository.findByEmail(form.getEmail());
        if (user == null || !"ROLE_ADMIN".equals(user.getRole())) {
            model.addAttribute("emailError", "ユーザーが登録されていません。");
            model.addAttribute("loginForm", form);
            return "admin/login";
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);
            return "redirect:/admin/products";
        } catch (BadCredentialsException e) {
            model.addAttribute("authError", "メールアドレス、またはパスワードが異なります。");
            model.addAttribute("loginForm", form);
            return "admin/login";
        }
    }
}
