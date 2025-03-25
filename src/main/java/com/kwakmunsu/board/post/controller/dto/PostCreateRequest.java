package com.kwakmunsu.board.post.controller.dto;

import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.util.JwtUtil;
import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest(

        @NotBlank(message = "게시글 제목을 입력해주세요")
        String title,

        @NotBlank(message = "게시글 내용을 입력해주세요")
        String content
) {

    public PostCreateCommand toPostCreateCommand() {
        Long memberId = JwtUtil.getCurrentMemberId();

        return PostCreateCommand.builder()
                .title(title)
                .content(content)
                .memberId(memberId)
                .build();
    }

}