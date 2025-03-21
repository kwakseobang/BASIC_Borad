package com.kwakmunsu.board.post.service.dto.request;

import com.kwakmunsu.board.util.JwtUtil;
import lombok.Builder;

@Builder
public record PostDeleteCommand(Long postId, Long memberId) {

    public static PostDeleteCommand from(Long postId) {
        return PostDeleteCommand.builder()
                .postId(postId)
                .memberId(JwtUtil.getCurrentMemberId())
                .build();
    }

}