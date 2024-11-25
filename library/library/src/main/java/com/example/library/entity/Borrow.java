package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username; // 유저 아이디

    @Column(nullable = false)
    private Integer bookId; // 대출한 책 ID

    @Column(nullable = false)
    private LocalDateTime borrowedAt = LocalDateTime.now(); // 대출 일자

    @Column(nullable = false)
    private LocalDateTime dueDate = borrowedAt.plusMonths(1); // 반납 기한

    @Column(nullable = false)
    private String status = "대출 중"; // 상태 (기본값: 대출 중)
}
