package com.example.ecsite.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import com.example.ecsite.form.LoginForm;
import com.example.ecsite.form.SignupForm;
import com.example.ecsite.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ユーザー認証（会員登録・ログイン）コントローラ。
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public AccountController(UserService userService,
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    /** ログイン画面を表示する */
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "accounts/login";
    }

    /**
     * ログイン処理
     * - メールアドレスの形式チェック
     * - 登録済みチェック
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
            return "accounts/login";
        }

        if (!userService.emailExists(form.getEmail())) {
            model.addAttribute("emailError", "ユーザーが登録されていません。");
            model.addAttribute("loginForm", form);
            return "accounts/login";
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);
            return "redirect:/products";
        } catch (BadCredentialsException e) {
            model.addAttribute("authError", "メールアドレス、またはパスワードが異なります。");
            model.addAttribute("loginForm", form);
            return "accounts/login";
        }
    }

    /** 会員登録画面を表示する */
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "accounts/signup";
    }

    /**
     * 会員登録処理
     * - メールアドレスの形式チェック・重複チェック
     * - パスワード一致チェック（最低8文字）
     * - ユーザー登録後、ログイン画面にリダイレクト
     */
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        boolean hasError = false;

        if (bindingResult.hasFieldErrors("email")) {
            model.addAttribute("emailError", "メールアドレスの形式が異なっています");
            hasError = true;
        } else if (userService.emailExists(form.getEmail())) {
            model.addAttribute("emailError", "ユーザーアカウントがすでに登録されています");
            hasError = true;
        }

        if (bindingResult.hasFieldErrors("password")) {
            model.addAttribute("passwordError", bindingResult.getFieldError("password").getDefaultMessage());
            hasError = true;
        } else if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("confirmPasswordError", "パスワードが異なります。");
            hasError = true;
        }

        if (hasError) {
            model.addAttribute("signupForm", form);
            return "accounts/signup";
        }

        userService.signup(form);
        redirectAttributes.addFlashAttribute("successMessage", "ユーザー登録が完了しました。");
        return "redirect:/accounts/login";
    }
}
