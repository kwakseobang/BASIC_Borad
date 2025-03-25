package com.kwakmunsu.board.comment.service.dto.request;

import lombok.Builder;

@Builder
public record CommentUpdateServiceRequest(
        String content
) {

}