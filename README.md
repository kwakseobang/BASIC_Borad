# 🦁 멋쟁이 사자처럼 13기 Spring 스터디 과제



## 📌 기능 요구 사항
- 게시판
    - 글 작성, 수정, 삭제
    - 조회 게시글 조회수 표시
    - 글 목록 표시(페이징) - 4가지 카테고리로 페이징 가능
      - 제목, 생성일, 글 번호, 조회수 좋아요 기능
      - 저장 기능
- 댓글
  - 작성, 수정, 삭제, 조회 
- 회원
  - 닉네임 변경
- 로그인 / 로그아웃 
- 회원 가입
- 당연한 예외처리(게시글의 작성자만 수정 가능 등)들은 직접 고민하시고 작성하시면 됩니다.


## ERD

![board (3)](https://github.com/user-attachments/assets/e1047390-9a7b-4cfb-ac91-4de91a1317d6)


## 🔥 Task (변동 될 수 있음)
1. 프로젝트 초기 셋팅
   - [x] README 작성
   - [x] 도메인 생성
   - [x] DB 연동
   - [x] Swagger 연동
   - [x] 에러 메세지 정의
   - [x] Custom ResponseEntity 생성
   - [x] 전역 예외 처리
2. 회원 생성 기능 구현
   - [x] 회원 생성 기능
3. JWT 인증 구현
   - [x] Security Config 설정
   - [x] 토큰 발급 로직 구현
   - [x] JwtFilter 구현
   - [x] 회원 로그인 기능 구현
   - [x] 토큰 재발급 기능 구현
4. 나머지 회원 기능 구현
    - [x] 로그아웃 기능 구현
    - [x] 닉네임 변경 기능 구현
5. 글 작성 기능 구현
6. 댓글 기능 구현
   - [x] 작성
   - [x] 수정
   - [x] 삭제
   - [x] 조회
7. 좋아요 기능 구현
8. 글 수정 기능 구현
8. 저장 기능 구현
9. 글 조회 기능 구현
   - [ ] 페이징 처리 기능 구현
10. 글 조회수 기능 구현
11. 글 삭제 기능 구현

## 예외 처리 방식
