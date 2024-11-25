package com.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor // 기본 생성자 추가 (JPA 사용 시 필수)
@Table(name = "book") // 테이블 이름 지정
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_name", nullable = false) // DB 컬럼 이름 지정
    private String bookName;

    @Column(name = "book_author", nullable = false)
    private String bookAuthor;

    @Column(name = "book_genre")
    private String bookGenre;

    @Column(name = "company")
    private String company;

    @Column(name = "sign")
    private String sign;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "no_of_copies", nullable = false) // 대출 가능한 수량
    private Integer noOfCopies;

    }
