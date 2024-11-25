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

    @ManyToOne(fetch = FetchType.LAZY) // Many-to-One 관계로 Book 엔티티와 연결
    @JoinColumn(name = "book_id", referencedColumnName = "id") // book_id 컬럼을 참조
    private Book book; // 대출한 책

    @Column(nullable = false)
    private LocalDateTime borrowedAt = LocalDateTime.now(); // 대출 일자

    @Column(nullable = false)
    private LocalDateTime dueDate = borrowedAt.plusMonths(1); // 반납 기한

    @Column(name = "status", nullable = false)
    private String status = "대출 중"; // 상태 (기본값: 대출 중)
}
