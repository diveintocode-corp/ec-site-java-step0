package com.example.ecsite.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.ecsite.entity.Cart;

@Mapper
public interface CartMapper {

    @Select("SELECT * FROM carts WHERE user_id = #{userId}")
    Cart findByUserId(Long userId);

    @Insert("INSERT INTO carts (user_id) VALUES (#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "cartId")
    void insert(Cart cart);
}
