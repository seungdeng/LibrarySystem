package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username; // 로그인용 사용자 이름

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String name; // 실제 이름

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
