package com.kwakmunsu.board.auth.controller;


import static com.kwakmunsu.board.global.jwt.common.TokenType.REFRESH;
import static com.kwakmunsu.board.util.CookieUtil.createCookie;

import com.kwakmunsu.board.auth.controller.dto.LoginRequest;
import com.kwakmunsu.board.auth.controller.dto.MemberCreateRequest;
import com.kwakmunsu.board.auth.service.AuthService;
import com.kwakmunsu.board.global.jwt.dto.MemberTokens;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입 [JWT 사용 X]")
    public ResponseEntity<ResponseData<?>> signUp(@RequestBody MemberCreateRequest request) {
        authService.signUp(request.toNewMember());
        return ResponseData.success(SuccessCode.CREATED_MEMBER);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 [JWT 사용 X]")
    public ResponseEntity<ResponseData<String>> login(
            HttpServletResponse response,
            @RequestBody LoginRequest request
    ) {
        MemberTokens memberTokens = authService.login(request.tologinDto());
        // 같은 이름이 있다면 기존에 있던 쿠키 덮어짐.
        response.addCookie(createCookie(REFRESH.getValue(), memberTokens.refreshToken()));
        return ResponseData.success(SuccessCode.LOGIN_SUCCESS, memberTokens.accessToken());
    }

}