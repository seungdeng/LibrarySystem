# 📚 도서 관리 시스템

도서 관리 시스템은 대학교와 같은 환경에서 사용자가 대출/반납, 도서 검색, 그리고 개인 대출 기록을 관리할 수 있도록 설계된 웹 애플리케이션입니다. <br>
Spring Boot와 MySQL을 기반으로 구축되었으며, 효율적인 데이터 관리를 위해 RESTful API와 JPA를 활용했습니다.
<br><br>

## 🌟 주요 기능

### 1. 사용자 관리
- 로그인 및 회원가입.
- 사용자별 대출 기록 관리.

### 2. 도서 관리
- 도서 등록, 수정, 삭제.
- 도서 검색 (책 이름, 저자, 출판사 기준).

### 3. 대출 및 반납 관리
- 대출 가능 여부 확인.
- 대출 기록 추가 및 반납 처리.

### 4. 검색 및 페이징
- 키워드 검색.
- 페이지당 15개씩 표시, 이전/다음 및 페이지 이동 기능.


## 🔧 기술 스택

- **Backend**: Spring Boot, Spring Data JPA, Hibernate, MySQL
- **Frontend**: Thymeleaf, HTML5, CSS3, JavaScript
- **Build Tool**: Maven
- **Deployment**: AWS EC2, AWS RDS

---

## 📂 프로젝트 구조
src/main/java/com/example/library <br>
├── controller # Spring MVC 컨트롤러 <br>
├── entity # 데이터베이스 엔티 <br>
├── repository # JPA 리포지토리 인터페이스 <br>
├── service # 서비스 계층 <br>
└── LibraryApplication.java # Spring Boot 메인 클래스
<br><br>

🚀 REST API<br>
도서 API<br>
GET /books: 도서 목록 및 검색 결과 반환.<br>
POST /books: 새 도서 추가.<br>
대출 API<br>
GET /borrows: 현재 사용자의 대출 기록 반환.<br>
POST /borrows/borrow: 도서 대출.<br>
POST /borrows/return: 도서 반납.<br>
사용자 API<br>
POST /users: 사용자 등록.<br><br>

🛠️ 기능 구현 상세
도서 검색 및 페이징
<br>
사용자는 검색어 입력과 페이지 이동 버튼을 통해 검색 결과를 탐색할 수 있습니다.<br>
각 페이지는 최대 15개의 도서를 표시합니다.<br>
<br>
대출 및 반납<br>
사용자는 대출 가능한 도서를 대출하거나, 이미 대출한 도서를 반납할 수 있습니다.<br>
대출 상태는 대출 중 및 반납 완료로 관리됩니다.<br>

