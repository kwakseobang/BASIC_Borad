package com.kwakmunsu.board.auth.controller;


import com.kwakmunsu.board.auth.controller.dto.LoginRequest;
import com.kwakmunsu.board.auth.controller.dto.MemberCreateRequest;
import com.kwakmunsu.board.global.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "AuthController", description = "Auth API")
public interface AuthApiController {

    @Operation(summary = "회원가입 [JWT 사용 X]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "아이디 중복으로 인한 실패"),
            @ApiResponse(responseCode = "409", description = "닉네임 중복으로 인한 실패")
    })
    ResponseEntity<ResponseData<?>> signUp(@RequestBody MemberCreateRequest request);

    @Operation(summary = "로그인 [JWT 사용 X]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공 토큰 발급"),
            @ApiResponse(responseCode = "401", description = "UnAuthentication"),
            @ApiResponse(responseCode = "404", description = "회원 id 불일치")
    })
    ResponseEntity<ResponseData<String>> login(
            HttpServletResponse response,
            @RequestBody LoginRequest request
    );

    @Operation(summary = "Access Token 재발급 요청 [쿠키 사용]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 재발급 성공"),
            @ApiResponse(responseCode = "401", description = "UnAuthentication"),
    })
    ResponseEntity<ResponseData<String>> reissue(
            HttpServletResponse response,
            @CookieValue("refreshToken") final String refreshTokenRequest
    );

}