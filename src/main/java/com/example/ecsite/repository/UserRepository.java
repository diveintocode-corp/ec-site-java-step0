package com.example.ecsite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ecsite.entity.User;
import com.example.ecsite.mapper.UserMapper;

/**
 * ユーザーデータへのアクセスを提供するリポジトリクラス。
 */
@Repository
public class UserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public User findById(Long userId) {
        return userMapper.findById(userId);
    }

    public List<User> findAllCustomers() {
        return userMapper.findAllCustomers();
    }

    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public int countByEmail(String email) {
        return userMapper.countByEmail(email);
    }
}
