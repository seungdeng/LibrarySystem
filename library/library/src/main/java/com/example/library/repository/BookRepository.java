package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // 검색 메서드: book_name, book_author, company에서 keyword 검색
    List<Book> findByBookNameContainingOrBookAuthorContainingOrCompanyContaining(String bookName, String bookAuthor, String company);
}
