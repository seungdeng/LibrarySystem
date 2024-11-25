package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.entity.Borrow;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    // 대출 처리
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam String username, @RequestParam Integer bookId, Model model) {
        try {
            System.out.println("대출 요청 username: " + username);

            // 유효성 검사: username이 존재하는지 확인
            if (!userService.existsByUsername(username)) {
                throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
            }

            // 책 정보 가져오기
            Book book = bookService.findById(bookId);
            if (book.getNoOfCopies() <= 0) {
                throw new IllegalStateException("해당 책은 대출 불가능합니다.");
            }

            // 대출 처리
            Borrow borrow = new Borrow();
            borrow.setUsername(username);
            borrow.setBookId(bookId);
            borrow.setBorrowedAt(LocalDateTime.now());
            borrow.setDueDate(LocalDateTime.now().plusMonths(1));
            borrowService.saveBorrow(borrow);

            // 수량 감소
            book.setNoOfCopies(book.getNoOfCopies() - 1);
            bookService.saveBook(book);

            return "redirect:/borrows?username=" + username; // 대출 완료 후 대출 목록 페이지로 이동

        } catch (IllegalArgumentException | IllegalStateException e) {
            // 오류 메시지를 모델에 추가
            model.addAttribute("error", e.getMessage());
            return "error"; // 에러 페이지로 이동
        }
    }

    // 유저 대출 기록 조회
    @GetMapping("/borrows")
    public String getUserBorrows(@RequestParam String username, Model model) {
        System.out.println("대출 기록 조회 username: " + username);
        List<Borrow> borrows = borrowService.getBorrowsByUsername(username);
        model.addAttribute("borrows", borrows);
        return "borrows"; // borrows.html로 이동
    }

    // 예외 처리: IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // error.html 페이지로 이동
    }

    // 예외 처리: IllegalStateException
    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // error.html 페이지로 이동
    }
}
