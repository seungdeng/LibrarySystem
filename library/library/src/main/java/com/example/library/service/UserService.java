package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자 존재 여부 확인 (대소문자 무시)
    public boolean existsByUsername(String username) {
        System.out.println("입력된 username: " + username);
        boolean exists = userRepository.findByUsername(username).isPresent();
        System.out.println("존재 여부: " + exists);
        return exists;
    }

    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 사용자 저장 (중복 체크 및 비밀번호 암호화)
    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // 사용자 검증 (로그인 시 사용)
    public User validateUser(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(null);
    }
}
