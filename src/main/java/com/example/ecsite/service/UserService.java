package com.example.ecsite.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecsite.entity.User;
import com.example.ecsite.form.SignupForm;
import com.example.ecsite.repository.UserRepository;

/**
 * ユーザー関連のビジネスロジックを提供するサービスクラス。
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * メールアドレスが既に登録済みかどうかを確認する。
     */
    public boolean emailExists(String email) {
        return userRepository.countByEmail(email) > 0;
    }

    /**
     * 新規ユーザーを登録する。
     */
    @Transactional
    public void signup(SignupForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setActive(true);
        user.setRole("ROLE_USER");
        userRepository.insert(user);
    }

    /**
     * IDでユーザーを取得する。
     */
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * 顧客一覧（管理者・スタッフ以外）を取得する。
     */
    public List<User> findAllCustomers() {
        return userRepository.findAllCustomers();
    }

    /**
     * ユーザー情報を更新する。
     */
    @Transactional
    public void updateUser(User user) {
        userRepository.update(user);
    }
}
