package com.kwakmunsu.board.comment.controller.dto;

import com.kwakmunsu.board.comment.service.dto.request.CommentCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CommentCreateRequest(

        @NotBlank(message = "댓글을 입력해주세요")
        String content,

        @NotNull(message = "게시글 ID를 입력해주세요")
        @Positive(message = "0보다 커야합니다")
        Long postId
) {

    public CommentCreateServiceRequest toServiceRequest() {

        return CommentCreateServiceRequest.builder()
                .content(content)
                .postId(postId)
                .build();
    }

}