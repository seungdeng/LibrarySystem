package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String keyword) {
        // BookRepository에 정의된 메서드 호출
        return bookRepository.findByBookNameContainingOrBookAuthorContainingOrCompanyContaining(keyword, keyword, keyword);
    }
}
