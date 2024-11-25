package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // HTML 렌더링 메서드
    @GetMapping
    public String getBooksPage(
            @RequestParam(value = "keyword", required = false) String keyword,
            HttpSession session,
            Model model
    ) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login"; // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
        }

        // 검색어가 존재하면 검색 결과를 반환, 아니면 전체 목록 반환
        List<Book> books;
        if (keyword != null && !keyword.trim().isEmpty()) {
            books = bookService.searchBooks(keyword);
        } else {
            books = bookService.getAllBooks();
        }

        // 모델에 데이터 전달
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword); // 검색어 유지

        return "books"; // books.html 템플릿 반환
    }
}
