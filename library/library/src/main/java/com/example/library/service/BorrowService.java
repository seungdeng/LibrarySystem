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

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }
}
