package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 책을 찾을 수 없습니다."));
    }
    // 페이징 없이 모든 책 가져오기 (기존 기능 유지)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 책 저장 (기존 기능 유지)
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // 페이징과 검색 기능 결합
    public Page<Book> getBooks(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // 페이징 정보 생성
        if (keyword != null && !keyword.isEmpty()) {
            // 검색 조건에 맞는 책 리스트를 페이징 처리
            return bookRepository.findByBookNameContainingOrBookAuthorContainingOrCompanyContaining(
                    keyword, keyword, keyword, pageable);
        } else {
            // 검색 조건이 없을 경우 전체 책 리스트를 페이징 처리
            return bookRepository.findAll(pageable);
        }
    }
}
