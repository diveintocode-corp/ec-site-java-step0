package com.example.ecsite;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.ecsite.entity.Profile;
import com.example.ecsite.entity.User;
import com.example.ecsite.repository.ProfileRepository;
import com.example.ecsite.repository.UserRepository;
import com.example.ecsite.service.S3StorageService;

/**
 * アプリケーション起動時に初期データを投入するクラス。
 *
 * 初期アカウント:
 *   管理者: admin@example.com / admin1234
 *   一般ユーザー: user@example.com / user1234
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;
    private final Optional<S3StorageService> s3;

    @Value("${app.upload-dir:./uploads}")
    private String uploadDir;

    @Value("${app.seed.products.enabled:false}")
    private boolean seedEnabled;

    public DataInitializer(UserRepository userRepository,
                           ProfileRepository profileRepository,
                           PasswordEncoder passwordEncoder,
                           JdbcTemplate jdbcTemplate,
                           PlatformTransactionManager transactionManager,
                           Optional<S3StorageService> s3) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
        this.s3 = s3;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedAdminUser();
        seedSampleUser();
        if (s3.isPresent() && seedEnabled) {
            seedProd();
        } else if (s3.isEmpty()) {
            copySeedImages();
        }
        log.info("Startup tasks completed successfully");
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

    private void seedProd() throws IOException {
        S3StorageService s3Service = s3.get();
        String baseUrl = s3Service.s3BaseUrl() + "/products";

        log.info("Production seed starting — uploading images to S3 and seeding DB");
        uploadSeedImagesToS3(s3Service);

        // DB seed runs atomically — all records commit or none do (fail fast on error)
        new TransactionTemplate(transactionManager).execute(status -> {
            try {
                executeSqlResource("classpath:seed/products.sql", null);
                executeSqlResource("classpath:seed/product_images.sql", baseUrl);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            return null;
        });

        // setval runs after commit — PostgreSQL sequences are non-transactional
        jdbcTemplate.execute(
            "SELECT setval(pg_get_serial_sequence('products', 'product_id'), (SELECT MAX(product_id) FROM products))");
        jdbcTemplate.execute(
            "SELECT setval(pg_get_serial_sequence('product_images', 'product_image_id'), (SELECT MAX(product_image_id) FROM product_images))");
    }

    private void uploadSeedImagesToS3(S3StorageService s3Service) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:seed/products/*.png");
        for (Resource resource : resources) {
            String key = "products/" + resource.getFilename();
            if (!s3Service.exists(key)) {
                byte[] bytes = resource.getInputStream().readAllBytes();
                uploadWithRetry(s3Service, key, bytes);
            }
        }
    }

    private void uploadWithRetry(S3StorageService s3Service, String key, byte[] bytes) {
        int maxAttempts = 3;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                s3Service.upload(key, bytes, "image/png");
                return;
            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    throw e;
                }
                log.warn("S3 upload failed for {} (attempt {}/{}): {}", key, attempt, maxAttempts, e.getMessage());
            }
        }
    }

    private void executeSqlResource(String location, String s3BaseUrl) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource(location);
        String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        if (s3BaseUrl != null) {
            sql = sql.replace("S3_BASE_URL", s3BaseUrl);
        }
        for (String stmt : sql.split(";")) {
            String trimmed = stmt.strip();
            if (!trimmed.isEmpty()) {
                jdbcTemplate.execute(trimmed);
            }
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
