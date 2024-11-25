package com.example.library.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(HttpSession session, Model model) {
        // 인증된 사용자 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 이름 가져오기
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            // 로그인된 사용자라면 principal은 UserDetails 타입
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername(); // 사용자 이름 가져오기
            }
        }

        // 세션에 사용자 정보 저장
        if (username != null) {
            session.setAttribute("userName", username);
        }

        // 모델에 세션 데이터 전달
        model.addAttribute("userName", session.getAttribute("userName"));

        return "home"; // home.html 반환
    }
}
