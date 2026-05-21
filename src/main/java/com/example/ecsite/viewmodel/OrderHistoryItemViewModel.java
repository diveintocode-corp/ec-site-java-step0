package com.example.ecsite.viewmodel;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/** 注文履歴1件分の表示用 ViewModel */
@Data
public class OrderHistoryItemViewModel {
    private Long orderId;
    private String orderNo;
    private String statusDisplay;
    private LocalDateTime createdAt;
    private List<OrderItemViewModel> items;
    private int subtotal;
    private int tax;
    private int total;
    private boolean canCancel;
}
