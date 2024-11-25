package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // 검색 결과를 페이징 처리하는 메서드
    Page<Book> findByBookNameContainingOrBookAuthorContainingOrCompanyContaining(
            String bookName, String bookAuthor, String company, Pageable pageable);
}
