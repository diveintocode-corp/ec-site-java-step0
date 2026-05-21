package com.example.ecsite.viewmodel;

import java.util.List;

import lombok.Data;

/** カート一覧画面用 ViewModel */
@Data
public class CartViewModel {
    private List<CartItemViewModel> items;
    private int totalEx;
    private int totalIn;
    private int taxRate;
}
