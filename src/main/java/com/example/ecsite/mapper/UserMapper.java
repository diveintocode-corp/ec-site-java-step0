package com.example.ecsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ecsite.entity.User;

@Mapper
public interface UserMapper {

        @Select("SELECT user_id, email, password, active, role," +
                        "created_at, updated_at FROM users WHERE email = #{email}")
        User findByEmail(String email);

        @Select("SELECT user_id, email, password, active, role," +
                        "created_at, updated_at FROM users WHERE user_id = #{userId}")
        User findById(Long userId);

        @Select("SELECT user_id, email, password, active, role," +
                        "created_at, updated_at FROM users " +
                        "WHERE role = 'ROLE_USER' ORDER BY created_at DESC")
        List<User> findAllCustomers();

        @Insert("INSERT INTO users (email, password, active, role) " +
                        "VALUES (#{email}, #{password}, #{active}, #{role})")
        @Options(useGeneratedKeys = true, keyProperty = "userId")
        void insert(User user);

        @Update("UPDATE users SET email = #{email}, active = #{active}, " +
                        "updated_at = CURRENT_TIMESTAMP WHERE user_id = #{userId}")
        void update(User user);

        @Select("SELECT COUNT(*) FROM users WHERE email = #{email}")
        int countByEmail(String email);
}
