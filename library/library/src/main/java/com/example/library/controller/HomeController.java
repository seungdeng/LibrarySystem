package com.example.library.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(HttpSession session, Model model) {
        // 세션에서 사용자 정보 확인 (null일 수도 있음)
        model.addAttribute("userName", session.getAttribute("userName"));
        return "home"; // home.html 반환
    }
}
