package com.kwakmunsu.board.post.controller.dto;

import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
import com.kwakmunsu.board.util.JwtUtil;
import jakarta.validation.constraints.Null;

public record PostUpdateRequest(

        @Null(message = "1글자 이상의 제목을 입력해주세요")
        String title,

        @Null(message = "1글자 이상의 내용을 입력해주세요")
        String content
) {

    public PostUpdateCommand toPostUpdateCommand(Long postId) {
        Long memberId = JwtUtil.getCurrentMemberId();

        return PostUpdateCommand.builder()
                .title(title)
                .content(content)
                .postId(postId)
                .memberId(memberId)
                .build();
    }

}