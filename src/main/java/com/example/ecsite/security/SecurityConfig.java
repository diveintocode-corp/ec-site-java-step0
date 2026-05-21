package com.example.ecsite.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * セキュリティ設定クラス。
 * 管理者用（/admin/**）と顧客用の2つのフィルターチェーンを定義する。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        @Order(1)
        public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
                http
                                .securityMatcher("/admin/**")
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/admin/login").permitAll()
                                                .anyRequest().hasRole("ADMIN"))
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(
                                                                new LoginUrlAuthenticationEntryPoint("/admin/login")))
                                .logout(logout -> logout
                                                .logoutUrl("/admin/logout")
                                                .logoutSuccessUrl("/admin/login"));
                return http.build();
        }

        @Bean
        @Order(2)
        public SecurityFilterChain customerFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/accounts/login", "/accounts/signup", "/error")
                                                .permitAll()
                                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                                .permitAll()
                                                .requestMatchers("/uploads/**").permitAll()
                                                .requestMatchers("/h2-console/**").permitAll()
                                                .requestMatchers("/", "/products", "/products/**").permitAll()
                                                .anyRequest().authenticated())
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(
                                                                new LoginUrlAuthenticationEntryPoint(
                                                                                "/accounts/login")))
                                .logout(logout -> logout
                                                .logoutUrl("/accounts/logout")
                                                .logoutSuccessUrl("/accounts/login"))
                                .csrf(csrf -> csrf
                                                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                                .headers(headers -> headers
                                                .frameOptions(frame -> frame.sameOrigin()));
                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        /**
         * AccountController でログイン認証を行うために公開する AuthenticationManager。
         */
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

        /**
         * ログイン成功後にセキュリティコンテキストをセッションに保存するリポジトリ。
         */
        @Bean
        public SecurityContextRepository securityContextRepository() {
                return new HttpSessionSecurityContextRepository();
        }
}
