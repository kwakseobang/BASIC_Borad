package com.kwakmunsu.board.member.controller;


import com.kwakmunsu.board.global.response.ResponseData;
import com.kwakmunsu.board.global.response.success.SuccessCode;
import com.kwakmunsu.board.member.controller.dto.NicknameRequest;
import com.kwakmunsu.board.member.service.MemberService;
import com.kwakmunsu.board.member.service.dto.NewNicknameDto;
import com.kwakmunsu.board.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        Long memberId = JwtUtil.getCurrentMemberId();
        NewNicknameDto newNicknameDto = nicknameRequest.toNewNicknameDto();
        memberService.updateNickname(memberId, newNicknameDto);

        return ResponseData.success(SuccessCode.UPDATE_NICKNAME);
    }

}