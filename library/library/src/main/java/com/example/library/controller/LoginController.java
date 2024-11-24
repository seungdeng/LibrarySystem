package com.example.library.controller;

import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html 반환
    }

    @PostMapping("/authenticate")
    public String authenticate(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // 데이터베이스에서 사용자 조회
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            // 세션에 사용자 이름 저장
            session.setAttribute("userName", optionalUser.get().getName());
            return "redirect:/"; // 인증 성공 시 메인 페이지로 리다이렉트
        } else {
            // 로그인 실패 시 에러 메시지
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login"; // login.html 반환
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
