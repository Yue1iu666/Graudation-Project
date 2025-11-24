// SecurityConfig.java
package com.example.index.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 禁用 CSRF（前后端分离通常需要）
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(
                    "/register",      // 注册接口
                    "/login",         // 登录接口（如果你有）
                    "/sms/send",      // 发送短信接口（示例）
                    "/**/*.html",     // 静态页面（可选）
                    "/**/*.js",
                    "/**/*.css"
                ).permitAll()         // 这些路径无需认证
                .anyRequest().authenticated() // 其他所有请求都需要认证
            )
            .httpBasic(); // 可选：保留 basic 认证（或删除）

        return http.build();
    }
}