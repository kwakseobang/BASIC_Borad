package com.kwakmunsu.board.auth.controller.dto;

import com.kwakmunsu.board.auth.service.dto.request.MemberCreateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberCreateRequest(

        @NotBlank(message = "사용자 id를 입력해주세요")
        String username,

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 8, message = "비밀번호는 최소 8자리 이상입니다")
        String password,

        @NotBlank(message = "닉네임을 입력해주세요")
        String nickname
) {

    public MemberCreateCommand toMemberCreateCommand() {
        return MemberCreateCommand.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

}