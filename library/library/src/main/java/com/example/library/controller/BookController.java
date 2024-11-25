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
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            HttpSession session,
            Model model
    ) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login";
        }

        int pageSize = 15; // 한 페이지에 표시할 항목 수
        Page<Book> bookPage = bookService.getBooks(keyword, page, pageSize);

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "books";
    }
}

