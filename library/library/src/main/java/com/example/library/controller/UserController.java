package com.example.library.controller;

import com.example.library.entity.User;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller // RestController에서 Controller로 변경
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // RESTful API: 모든 사용자 조회
    @GetMapping
    @ResponseBody // JSON으로 반환
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // RESTful API: 사용자 저장
    @PostMapping
    @ResponseBody // JSON으로 반환
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // HTML 뷰: 회원가입 폼 표시
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User()); // 빈 사용자 객체 전달
        return "signup"; // signup.html 뷰 반환
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, Model model) {
        try {
            userService.saveUser(user); // 사용자 저장
            model.addAttribute("message", "회원가입이 완료되었습니다. 로그인하세요.");
            return "redirect:/login"; // 성공 시 로그인 페이지로 이동
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "signup"; // 실패 시 다시 회원가입 페이지로 이동
        }
    }

}
