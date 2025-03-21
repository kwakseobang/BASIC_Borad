package com.kwakmunsu.board.comment.controller.dto;

import com.kwakmunsu.board.comment.service.dto.request.CommentCreateCommand;
import com.kwakmunsu.board.util.JwtUtil;

public record CommentCreateRequest(String content, Long postId) {

    public CommentCreateCommand toCommentCreateCommand() {
        Long memberId = JwtUtil.getCurrentMemberId();

        return CommentCreateCommand.builder()
                .content(content)
                .postId(postId)
                .writerId(memberId)
                .build();
    }

}