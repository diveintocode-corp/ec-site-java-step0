package com.example.ecsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ecsite.entity.Order;

@Mapper
public interface OrderMapper {

        @Select("SELECT * FROM orders WHERE user_id = #{userId} ORDER BY created_at DESC")
        List<Order> findByUserId(Long userId);

        @Select("SELECT * FROM orders ORDER BY created_at DESC")
        List<Order> findAll();

        @Select("SELECT * FROM orders WHERE order_id = #{orderId}")
        Order findById(Long orderId);

        @Insert("INSERT INTO orders (user_id, order_no, ship_name, ship_postal_code, " +
                        "ship_prefecture, ship_address1, ship_address2, ship_telno, " +
                        "subtotal_price, tax, total_price, status) " +
                        "VALUES (#{userId}, #{orderNo}, #{shipName}, #{shipPostalCode}, " +
                        "#{shipPrefecture}, #{shipAddress1}, #{shipAddress2}, #{shipTelno}, " +
                        "#{subtotalPrice}, #{tax}, #{totalPrice}, #{status})")
        @Options(useGeneratedKeys = true, keyProperty = "orderId")
        void insert(Order order);

        @Update("UPDATE orders SET ship_name = #{shipName}, ship_postal_code = #{shipPostalCode}, " +
                        "ship_prefecture = #{shipPrefecture}, ship_address1 = #{shipAddress1}, " +
                        "ship_address2 = #{shipAddress2}, ship_telno = #{shipTelno}, " +
                        "status = #{status}, updated_at = CURRENT_TIMESTAMP WHERE order_id = #{orderId}")
        void update(Order order);

        @Select("SELECT SUM(total_price) FROM orders WHERE user_id = #{userId} AND status = 'shipped'")
        Integer sumTotalPriceShippedByUserId(Long userId);
}
