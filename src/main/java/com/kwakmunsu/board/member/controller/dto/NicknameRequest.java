package com.kwakmunsu.board.member.controller.dto;

import com.kwakmunsu.board.member.service.dto.NicknameCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;

public record NicknameRequest(

        @NotBlank(message = "변경하실 닉네임을 입력해주세요")
        String nickname
) {

    public NicknameCreateServiceRequest toServiceRequest() {
        return NicknameCreateServiceRequest.builder()
                .nickname(nickname)
                .build();
    }

}