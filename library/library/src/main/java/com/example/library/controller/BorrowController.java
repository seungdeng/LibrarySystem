package com.example.library.controller;

import com.example.library.entity.Borrow;
import com.example.library.service.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BorrowController {
    @GetMapping("/borrows")
    public String borrowsPage(HttpSession session, Model model) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login"; // 로그인되지 않은 사용자는 로그인 페이지로 리다이렉트
        }
        return "borrows"; // borrows.html 반환
    }
}
