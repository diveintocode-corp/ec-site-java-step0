package com.example.ecsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ecsite.entity.ShippingAddress;

@Mapper
public interface ShippingAddressMapper {

    @Select("SELECT shipping_address_id, user_id, name, postal_code, prefecture, " +
            "address_1 AS address1, address_2 AS address2, telno, created_at, updated_at " +
            "FROM shipping_addresses WHERE user_id = #{userId} ORDER BY created_at ASC")
    List<ShippingAddress> findByUserId(Long userId);

    @Select("SELECT shipping_address_id, user_id, name, postal_code, prefecture, " +
            "address_1 AS address1, address_2 AS address2, telno, created_at, updated_at " +
            "FROM shipping_addresses WHERE shipping_address_id = #{id} AND user_id = #{userId}")
    ShippingAddress findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Insert("INSERT INTO shipping_addresses (user_id, name, postal_code, prefecture, address_1, address_2, telno) " +
            "VALUES (#{userId}, #{name}, #{postalCode}, #{prefecture}, #{address1}, #{address2}, #{telno})")
    @Options(useGeneratedKeys = true, keyProperty = "shippingAddressId")
    void insert(ShippingAddress addr);

    @Update("UPDATE shipping_addresses SET name = #{name}, postal_code = #{postalCode}, " +
            "prefecture = #{prefecture}, address_1 = #{address1}, address_2 = #{address2}, " +
            "telno = #{telno}, updated_at = CURRENT_TIMESTAMP " +
            "WHERE shipping_address_id = #{shippingAddressId} AND user_id = #{userId}")
    void update(ShippingAddress addr);

    @Delete("DELETE FROM shipping_addresses WHERE shipping_address_id = #{id} AND user_id = #{userId}")
    void deleteByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
}
