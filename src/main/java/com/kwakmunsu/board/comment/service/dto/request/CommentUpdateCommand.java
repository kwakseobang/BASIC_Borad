package com.kwakmunsu.board.comment.service.dto.request;

import lombok.Builder;

@Builder
public record CommentUpdateCommand(String content, Long commentId) {

}