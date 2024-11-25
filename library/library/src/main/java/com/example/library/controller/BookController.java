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
import org.springframework.data.domain.Page;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // HTML 렌더링 메서드
    @GetMapping
    public String getBooksPage(
            @RequestParam(value = "keyword", required = false) String keyword, // 검색어
            @RequestParam(value = "page", defaultValue = "0") int page, // 페이지 번호
            HttpSession session,
            Model model
    ) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login"; // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
        }

        int pageSize = 15; // 페이지당 항목 수

        // 검색 및 페이징 처리
        Page<Book> bookPage = bookService.getBooks(keyword, page, pageSize);

        // 모델에 데이터 전달
        model.addAttribute("books", bookPage.getContent()); // 현재 페이지 데이터
        model.addAttribute("totalPages", bookPage.getTotalPages()); // 전체 페이지 수
        model.addAttribute("currentPage", page); // 현재 페이지 번호
        model.addAttribute("keyword", keyword); // 검색어 유지

        return "books"; // books.html 템플릿 반환
    }
}
