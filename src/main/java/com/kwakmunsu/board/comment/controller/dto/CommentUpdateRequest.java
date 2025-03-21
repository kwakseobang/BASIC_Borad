package com.kwakmunsu.board.comment.controller.dto;

import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateCommand;

public record CommentUpdateRequest(String content) {

    public CommentUpdateCommand toCommentUpdateCommand(Long commentId) {
        return CommentUpdateCommand.builder()
                .content(content)
                .commentId(commentId)
                .build();
    }

}