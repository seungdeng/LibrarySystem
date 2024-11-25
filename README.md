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
├── entity # 데이터베이스 엔티티 <br>
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


## 📄 Application Properties 설정

이 프로젝트는 데이터베이스 연결 및 기타 환경 구성을 위해 `application.properties` 파일을 필요로 합니다. 이 파일은 **`src/main/resources/application.properties`** 경로에 생성해야 하며, 다음과 같은 내용을 포함해야 합니다:

```properties
# 서버 포트 설정
server.port=<YOUR_SERVER_PORT>

# 데이터베이스 설정
spring.datasource.url=jdbc:mysql://<DATABASE_HOST>:<PORT>/<DATABASE_NAME>
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate 및 JPA 설정(예시)
spring.jpa.hibernate.ddl-auto = update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true
server.servlet.session.timeout=30m
spring.thymeleaf.cache=false

⚠️ 주의 사항
보안: application.properties 파일에는 민감한 정보가 포함될 수 있으므로,
절대 버전 관리 시스템(Git)에 커밋하지 마세요.

관리 방법:
로컬 환경에서는 .gitignore 파일에 application.properties를 추가하여 Git 추적에서 제외합니다.
배포 환경에서는 환경 변수나 CI/CD 도구를 통해 동적으로 설정하세요.
