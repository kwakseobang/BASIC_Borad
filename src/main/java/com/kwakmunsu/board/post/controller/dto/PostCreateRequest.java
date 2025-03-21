package com.kwakmunsu.board.post.controller.dto;

import com.kwakmunsu.board.post.service.dto.request.PostCommand;
import com.kwakmunsu.board.util.JwtUtil;

public record PostCreateRequest(String title, String content) {

    public PostCommand toPostCreateCommand() {
        Long memberId = JwtUtil.getCurrentMemberId();
        return PostCommand.builder()
                .title(title)
                .content(content)
                .memberId(memberId)
                .build();
    }

}