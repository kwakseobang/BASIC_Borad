package com.kwakmunsu.board.post.controller.dto;

import com.kwakmunsu.board.post.service.dto.request.PostCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest(

        @NotBlank(message = "게시글 제목을 입력해주세요")
        String title,

        @NotBlank(message = "게시글 내용을 입력해주세요")
        String content
) {

    public PostCreateServiceRequest toServiceRequest() {
        return PostCreateServiceRequest.builder()
                .title(title)
                .content(content)
                .build();
    }

}