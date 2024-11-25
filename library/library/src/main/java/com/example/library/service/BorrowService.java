package com.example.library.service;

import com.example.library.entity.Borrow;
import com.example.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
