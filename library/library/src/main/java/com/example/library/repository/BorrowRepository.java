package com.example.library.repository;

import com.example.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    List<Borrow> findByUsername(String username); // 유저별 대출 기록 조회
}
