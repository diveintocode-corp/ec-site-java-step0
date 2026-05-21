package com.example.ecsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.ecsite.entity.OrderItem;

@Mapper
public interface OrderItemMapper {

    @Select("SELECT * FROM order_items WHERE order_id = #{orderId}")
    List<OrderItem> findByOrderId(Long orderId);

    @Insert("INSERT INTO order_items (order_id, product_id, unit_price, quantity) " +
            "VALUES (#{orderId}, #{productId}, #{unitPrice}, #{quantity})")
    @Options(useGeneratedKeys = true, keyProperty = "orderItemId")
    void insert(OrderItem orderItem);
}
