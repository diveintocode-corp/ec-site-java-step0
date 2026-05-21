package com.example.ecsite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.Order;
import com.example.ecsite.mapper.OrderMapper;

/**
 * 注文データへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class OrderRepository {

    private final OrderMapper orderMapper;

    public OrderRepository(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Order> findByUserId(Long userId) {
        return orderMapper.findByUserId(userId);
    }

    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    public Order findById(Long orderId) {
        return orderMapper.findById(orderId);
    }

    public void insert(Order order) {
        orderMapper.insert(order);
    }

    public void update(Order order) {
        orderMapper.update(order);
    }

    public Integer sumTotalPriceShippedByUserId(Long userId) {
        return orderMapper.sumTotalPriceShippedByUserId(userId);
    }
}
