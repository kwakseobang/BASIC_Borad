package com.kwakmunsu.board.post.controller.dto;

import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
import com.kwakmunsu.board.util.JwtUtil;

public record PostUpdateRequest(String title, String content) {

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