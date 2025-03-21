package com.kwakmunsu.board.post.controller.dto;

import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.util.JwtUtil;

public record PostCreateRequest(String title, String content) {

    public PostCreateCommand toPostCreateCommand() {
        Long memberId = JwtUtil.getCurrentMemberId();
        return PostCreateCommand.builder()
                .title(title)
                .content(content)
                .memberId(memberId)
                .build();
    }

}