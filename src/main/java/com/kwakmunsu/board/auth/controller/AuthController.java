package com.kwakmunsu.board.auth.controller;


import com.kwakmunsu.board.auth.controller.dto.MemberCreateRequest;
import com.kwakmunsu.board.auth.service.AuthService;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<ResponseData> signUp(@RequestBody MemberCreateRequest request) {
        authService.signUp(request.toNewMember());
        return ResponseData.success(SuccessCode.CREATED_MEMBER);
    }

}