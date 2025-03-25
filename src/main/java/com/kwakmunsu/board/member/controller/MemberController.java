package com.kwakmunsu.board.member.controller;

import static com.kwakmunsu.board.global.jwt.common.TokenType.REFRESH;
import static com.kwakmunsu.board.global.response.ResponseData.success;

import com.kwakmunsu.board.global.annotation.CurrentLoginMember;
import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.member.controller.dto.NicknameRequest;
import com.kwakmunsu.board.member.service.MemberService;
import com.kwakmunsu.board.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController implements MemberDocsController {

    private final MemberService memberService;

    @PutMapping
    public ResponseEntity<ResponseData<?>> updateNickname(
            @CurrentLoginMember Long memberId,
            @Valid @RequestBody NicknameRequest nicknameRequest
    ) {
        memberService.updateNickname(memberId, nicknameRequest.toServiceRequest());
        return success(SuccessCode.UPDATE_NICKNAME);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseData<?>> logout(
            @CurrentLoginMember Long memberId,
            HttpServletResponse response
    ) {
        memberService.logout(memberId);

        Cookie initCookie = CookieUtil.delete(REFRESH.getValue());
        response.addCookie(initCookie);

        return success(SuccessCode.LOGOUT_SUCCESS);
    }

}