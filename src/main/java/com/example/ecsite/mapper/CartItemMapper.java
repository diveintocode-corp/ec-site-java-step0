package com.example.ecsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ecsite.entity.CartItem;

@Mapper
public interface CartItemMapper {

    @Select("SELECT * FROM cart_items WHERE cart_id = #{cartId}")
    List<CartItem> findByCartId(Long cartId);

    @Select("SELECT * FROM cart_items WHERE cart_item_id = #{id}")
    CartItem findById(Long id);

    @Select("SELECT * FROM cart_items WHERE cart_id = #{cartId} AND product_id = #{productId}")
    CartItem findByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Insert("INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (#{cartId}, #{productId}, #{quantity})")
    @Options(useGeneratedKeys = true, keyProperty = "cartItemId")
    void insert(CartItem cartItem);

    @Update("UPDATE cart_items SET quantity = #{quantity}, " +
            "updated_at = CURRENT_TIMESTAMP WHERE cart_item_id = #{cartItemId}")
    void update(CartItem cartItem);

    @Delete("DELETE FROM cart_items WHERE cart_item_id = #{id}")
    void deleteById(Long id);

    @Delete("DELETE FROM cart_items WHERE cart_id = #{cartId}")
    void deleteByCartId(Long cartId);
}
