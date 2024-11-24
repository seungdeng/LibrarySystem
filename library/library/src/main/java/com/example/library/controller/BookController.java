package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getBooksPage(HttpSession session,Model model) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login"; // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
        }

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books); // HTML에 전달할 데이터
        return "books"; // books.html 템플릿 반환
    }
}
