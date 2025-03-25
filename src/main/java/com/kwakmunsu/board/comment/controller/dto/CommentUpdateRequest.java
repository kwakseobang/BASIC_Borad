package com.kwakmunsu.board.comment.controller.dto;

import com.kwakmunsu.board.comment.service.dto.request.CommentUpdateServiceRequest;
import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequest(

        @NotBlank(message = "댓글을 입력해주세요")
        String content
) {

    public CommentUpdateServiceRequest toServiceRequest() {
        return CommentUpdateServiceRequest.builder()
                .content(content)
                .build();
    }

}