package com.example.ecsite.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ecsite.entity.Profile;

@Mapper
public interface ProfileMapper {

        @Select("SELECT profile_id, user_id, name, postal_code, prefecture, " +
                        "address_1 AS address1, address_2 AS address2, telno, " +
                        "created_at, updated_at FROM profiles WHERE user_id = #{userId}")
        Profile findByUserId(Long userId);

        @Insert("INSERT INTO profiles (user_id, name, postal_code, prefecture, address_1, address_2, telno) " +
                        "VALUES (#{userId}, #{name}, #{postalCode}, #{prefecture}, #{address1}, #{address2}, #{telno})")
        @Options(useGeneratedKeys = true, keyProperty = "profileId")
        void insert(Profile profile);

        @Update("UPDATE profiles SET name = #{name}, postal_code = #{postalCode}, " +
                        "prefecture = #{prefecture}, address_1 = #{address1}, address_2 = #{address2}, " +
                        "telno = #{telno}, updated_at = CURRENT_TIMESTAMP WHERE profile_id = #{profileId}")
        void update(Profile profile);
}
