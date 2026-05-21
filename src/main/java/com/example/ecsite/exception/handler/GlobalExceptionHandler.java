package com.example.ecsite.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecsite.exception.OrderNotFoundException;
import com.example.ecsite.exception.ProductNotFoundException;

/**
 * アプリケーション全体の例外を処理するグローバルハンドラー。
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 商品が見つからない場合、商品一覧にリダイレクトする。
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFound(ProductNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/products";
    }

    /**
     * 注文が見つからない場合、注文履歴にリダイレクトする。
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public String handleOrderNotFound(OrderNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/order-history";
    }
}
