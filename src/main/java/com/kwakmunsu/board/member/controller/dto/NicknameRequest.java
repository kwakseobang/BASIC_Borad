package com.kwakmunsu.board.member.controller.dto;

import com.kwakmunsu.board.member.service.dto.NicknameCreateCommand;
import com.kwakmunsu.board.util.JwtUtil;
import jakarta.validation.constraints.NotBlank;

public record NicknameRequest(

        @NotBlank(message = "변경하실 닉네임을 입력해주세요")
        String nickname
) {

    public NicknameCreateCommand toNicknameCreateCommand() {
        Long memberId = JwtUtil.getCurrentMemberId();

        return NicknameCreateCommand.builder()
                .nickname(nickname)
                .memberId(memberId)
                .build();
    }

}