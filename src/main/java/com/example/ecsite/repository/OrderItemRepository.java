package com.example.ecsite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.OrderItem;
import com.example.ecsite.mapper.OrderItemMapper;

/**
 * 注文アイテムデータへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class OrderItemRepository {

    private final OrderItemMapper orderItemMapper;

    public OrderItemRepository(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }

    public void insert(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }
}
