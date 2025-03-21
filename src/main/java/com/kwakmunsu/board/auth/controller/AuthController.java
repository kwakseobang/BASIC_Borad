package com.kwakmunsu.board.auth.controller;


import static com.kwakmunsu.board.global.jwt.common.TokenType.REFRESH;
import static com.kwakmunsu.board.util.CookieUtil.create;

import com.kwakmunsu.board.auth.controller.dto.LoginRequest;
import com.kwakmunsu.board.auth.controller.dto.MemberCreateRequest;
import com.kwakmunsu.board.auth.service.AuthService;
import com.kwakmunsu.board.auth.service.dto.ReissueTokenCommand;
import com.kwakmunsu.board.global.jwt.dto.MemberTokens;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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

    @Operation(summary = "회원가입 [JWT 사용 X]")
    @PostMapping("/signup")
    public ResponseEntity<ResponseData<?>> signUp(@RequestBody MemberCreateRequest request) {
        authService.signUp(request.toMemberCreateCommand());
        return ResponseData.success(SuccessCode.CREATED_MEMBER);
    }

    @Operation(summary = "로그인 [JWT 사용 X]")
    @PostMapping("/login")
    public ResponseEntity<ResponseData<String>> login(
            HttpServletResponse response,
            @RequestBody LoginRequest request
    ) {
        MemberTokens memberTokens = authService.login(request.tologinCommand());
        // 같은 이름이 있다면 기존에 있던 쿠키 덮어짐.
        Cookie cookie = CookieUtil.create(REFRESH.getValue(), memberTokens.refreshToken());
        response.addCookie(cookie);

        return ResponseData.success(SuccessCode.LOGIN_SUCCESS, memberTokens.accessToken());
    }


    @Operation(summary = "Access Token 재발급 요청 [쿠키 사용]")
    @PostMapping("/reissue")
    public ResponseEntity<ResponseData<String>> reissue(
            HttpServletResponse response,
            @CookieValue("refreshToken") final String refreshTokenRequest
    ) {
        MemberTokens memberTokens = authService.reissue(
                new ReissueTokenCommand(refreshTokenRequest)
        );
        Cookie cookie = create(REFRESH.getValue(), memberTokens.refreshToken());
        response.addCookie(cookie);

        return ResponseData.success(SuccessCode.REISSUE_SUCCESS, memberTokens.accessToken());
    }

}