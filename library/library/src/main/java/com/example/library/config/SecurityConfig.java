package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home","/login", "/signup","/users/signup", "/css/**", "/js/**").permitAll() // / 및 /home 접근 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .sessionManagement(session -> session
                        .sessionFixation().none() // 기존 세션 유지
                )
                .formLogin(form -> form
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/home", true) // 로그인 성공 후 이동할 페이지
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // 로그아웃 성공 후 /로 이동
                        .permitAll()
                );

        return http.build();
    }


}
