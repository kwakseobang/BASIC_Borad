package com.kwakmunsu.board.auth.controller;

import static com.kwakmunsu.board.global.jwt.common.TokenType.REFRESH;
import static com.kwakmunsu.board.global.response.ResponseData.success;
import static com.kwakmunsu.board.util.CookieUtil.create;

import com.kwakmunsu.board.auth.controller.dto.LoginRequest;
import com.kwakmunsu.board.auth.controller.dto.MemberCreateRequest;
import com.kwakmunsu.board.auth.service.AuthService;
import com.kwakmunsu.board.global.jwt.dto.MemberTokens;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController implements AuthDocsController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseData<?>> signUp(
            @Valid @RequestBody MemberCreateRequest request
    ) {
        authService.signUp(request.toServiceRequest());
        return success(SuccessCode.CREATED_MEMBER);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<String>> login(
            HttpServletResponse response,
            @Valid @RequestBody LoginRequest request
    ) {
        MemberTokens memberTokens = authService.login(request.toServiceRequest());
        // 같은 이름이 있다면 기존에 있던 쿠키 덮어짐.
        addCookie(response, memberTokens);
        return success(SuccessCode.LOGIN_SUCCESS, memberTokens.accessToken());
    }

    @PostMapping("/reissue")
    public ResponseEntity<ResponseData<String>> reissue(
            HttpServletResponse response,
            @CookieValue("refreshToken") final String reissueToken
    ) {
        MemberTokens memberTokens = authService.reissue(reissueToken);
        addCookie(response, memberTokens);
        return success(SuccessCode.REISSUE_SUCCESS, memberTokens.accessToken());
    }

    private void addCookie(HttpServletResponse response, MemberTokens memberTokens) {
        Cookie cookie = CookieUtil.create(REFRESH.getValue(), memberTokens.refreshToken());
        response.addCookie(cookie);
    }

}