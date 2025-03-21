package com.kwakmunsu.board.member.controller;


import static com.kwakmunsu.board.global.jwt.common.TokenType.REFRESH;

import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.member.controller.dto.NicknameRequest;
import com.kwakmunsu.board.member.service.MemberService;
import com.kwakmunsu.board.member.service.dto.NicknameCreateCommand;
import com.kwakmunsu.board.util.CookieUtil;
import com.kwakmunsu.board.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "member")
@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PutMapping
    public ResponseEntity<ResponseData<?>> updateNickname(
            @RequestBody NicknameRequest nicknameRequest
    ) {
        NicknameCreateCommand nicknameCreateCommand = nicknameRequest.toNicknameCreateCommand();
        memberService.updateNickname(nicknameCreateCommand);

        return ResponseData.success(SuccessCode.UPDATE_NICKNAME);
    }

    // 클라이언트에서 AccessToken 삭제해주어야 함.
    // TODO: 보안 강화 로직 추가 가능
    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    public ResponseEntity<ResponseData<?>> logout(HttpServletResponse response) {
        // TODO: getCurrentMemberId()를 어느 레이어에서 호출 할 지 고민이다..
        Long memberId = JwtUtil.getCurrentMemberId();
        memberService.logout(memberId);

        Cookie initCookie = CookieUtil.delete(REFRESH.getValue());
        response.addCookie(initCookie);

        return ResponseData.success(SuccessCode.LOGOUT_SUCCESS);
    }

}