package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.entity.Borrow;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import com.example.library.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String borrowBook(@RequestParam Integer bookId, HttpSession session, Model model) {
        // 세션에서 username 가져오기
        String username = (String) session.getAttribute("userName");

        if (username == null) {
            model.addAttribute("error", "로그인 정보가 없습니다.");
            return "error"; // 로그인 정보가 없으면 에러 페이지로 이동
        }

        try {
            System.out.println("대출 요청 username: " + username);

            // 책 정보 가져오기
            Book book = bookService.findById(bookId);
            if (book.getNoOfCopies() <= 0) {
                throw new IllegalStateException("해당 책은 대출 불가능합니다.");
            }

            // 대출 처리
            Borrow borrow = new Borrow();
            borrow.setUsername(username);
            borrow.setBook(book); // book 객체를 대출에 연결
            borrow.setBorrowedAt(LocalDateTime.now());
            borrow.setDueDate(LocalDateTime.now().plusMonths(1));
            borrowService.saveBorrow(borrow);

            // 수량 감소
            book.setNoOfCopies(book.getNoOfCopies() - 1);
            bookService.saveBook(book);

            return "redirect:/borrows"; // 대출 완료 후 대출 목록 페이지로 이동

        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "error"; // 에러 페이지로 이동
        }
    }

    // 유저 대출 기록 조회
    @GetMapping("/borrows")
    public String getUserBorrows(HttpSession session, Model model) {
        // 세션에서 username 가져오기
        String username = (String) session.getAttribute("userName");

        if (username == null) {
            model.addAttribute("error", "로그인 정보가 없습니다.");
            return "error"; // 로그인 정보가 없으면 에러 페이지로 이동
        }

        System.out.println("대출 기록 조회 username: " + username);
        List<Borrow> borrows = borrowService.getBorrowsByUsername(username);

        model.addAttribute("borrows", borrows); // Borrow 객체들을 그대로 모델에 추가
        model.addAttribute("username", username); // 세션에 있는 username도 전달
        return "borrows"; // borrows.html 반환
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Integer borrowId, HttpSession session, Model model) {
        // 세션에서 username 가져오기
        String username = (String) session.getAttribute("userName");

        if (username == null) {
            model.addAttribute("error", "로그인 정보가 없습니다.");
            return "error"; // 로그인 정보가 없으면 에러 페이지로 이동
        }

        try {
            Borrow borrow = borrowService.findByUsernameAndId(username, borrowId); // 예외가 발생하면 catch로 넘어감

            Book book = bookService.findById(borrow.getBook().getId()); // borrow에서 book 객체 가져오기
            if (book == null) {
                model.addAttribute("error", "해당 책을 찾을 수 없습니다.");
                return "error";
            }

            // 대출 상태가 "반납 완료"인 경우에는 반납 버튼을 숨김
            if (borrow.getStatus() != null && borrow.getStatus().equals("반납 완료")) {
                model.addAttribute("error", "이미 반납된 도서입니다.");
                return "error";
            }

            // 반납 처리
            borrow.setStatus("반납 완료");
            borrowService.saveBorrow(borrow); // 상태 저장
            book.setNoOfCopies(book.getNoOfCopies() + 1); // 책 수량 증가
            bookService.saveBook(book);

            return "redirect:/borrows"; // 대출 목록 페이지로 리다이렉트
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
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
