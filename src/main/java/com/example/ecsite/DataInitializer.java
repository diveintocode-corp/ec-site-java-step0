package com.example.ecsite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.entity.User;
import com.example.ecsite.repository.ProfileRepository;
import com.example.ecsite.repository.UserRepository;

/**
 * アプリケーション起動時に初期データを投入するクラス。
 *
 * 初期アカウント:
 *   管理者: admin@example.com / admin1234
 *   一般ユーザー: user@example.com / user1234
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.upload-dir:./uploads}")
    private String uploadDir;

    @Value("${spring.profiles.active:default}")
    private String activeProfiles;

    public DataInitializer(UserRepository userRepository,
                           ProfileRepository profileRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedAdminUser();
        seedSampleUser();
        if (!activeProfiles.contains("prod")) {
            copySeedImages();
        }
    }

    private void seedAdminUser() {
        if (userRepository.countByEmail("admin@example.com") == 0) {
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setActive(true);
            admin.setRole("ROLE_ADMIN");
            userRepository.insert(admin);
        }
    }

    private void seedSampleUser() {
        if (userRepository.countByEmail("user@example.com") == 0) {
            User user = new User();
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user1234"));
            user.setActive(true);
            user.setRole("ROLE_USER");
            userRepository.insert(user);

            Profile profile = new Profile();
            profile.setUserId(user.getUserId());
            profile.setName("サンプルユーザー");
            profile.setPostalCode("1000001");
            profile.setPrefecture("東京都");
            profile.setAddress1("千代田区千代田1-1");
            profile.setAddress2("");
            profile.setTelno("09012345678");
            profileRepository.insert(profile);
        }
    }

    private void copySeedImages() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:seed/products/*.png");
        if (resources.length == 0) {
            return;
        }
        Path dest = Paths.get(uploadDir, "products");
        Files.createDirectories(dest);
        for (Resource resource : resources) {
            Path target = dest.resolve(resource.getFilename());
            if (!Files.exists(target)) {
                try (var in = resource.getInputStream()) {
                    Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}
