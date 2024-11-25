package com.example.library.service;

import com.example.library.entity.Borrow;
import com.example.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    // 대출 기록 저장
    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    // 유저별 대출 기록 조회
    public List<Borrow> getBorrowsByUsername(String username) {
        return borrowRepository.findByUsername(username);
    }
    // username과 borrowId로 대출 기록을 찾기 (반납 버튼을 위한 처리)
    public Borrow findByUsernameAndId(String username, Integer borrowId) {
        return borrowRepository.findById(borrowId)
                .filter(borrow -> borrow.getUsername().equals(username))  // username이 일치하는지 확인
                .orElseThrow(() -> new IllegalArgumentException("해당 ID로 대출 기록을 찾을 수 없습니다."));
    }
}
