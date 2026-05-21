package com.example.ecsite;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.ecsite.entity.User;
import com.example.ecsite.repository.UserRepository;

/**
 * アプリケーション起動時に初期データを投入するクラス。
 * 管理者ユーザーが存在しない場合のみ作成する。
 *
 * 初期管理者: admin@example.com / admin1234
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.countByEmail("admin@example.com") == 0) {
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setActive(true);
            admin.setRole("ROLE_ADMIN");
            userRepository.insert(admin);
        }
    }
}
