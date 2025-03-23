package com.kwakmunsu.board.member.controller;


import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.member.controller.dto.NicknameRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "MemberController", description = "Member API")
public interface MemberApiController {

    @Operation(summary = "닉네임 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "닉네임 변경 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 회원 입니다."),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 닉네임 입니다.")
    })
    ResponseEntity<ResponseData<?>> updateNickname(@RequestBody NicknameRequest nicknameRequest);

    @Operation(summary = "로그아웃")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "401", description = "로그인이 되어 있지않습니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 회원 입니다."),
    })
    ResponseEntity<ResponseData<?>> logout(HttpServletResponse response);

}