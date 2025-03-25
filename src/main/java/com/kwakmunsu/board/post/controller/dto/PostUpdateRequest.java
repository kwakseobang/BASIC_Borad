package com.kwakmunsu.board.post.controller.dto;

import com.kwakmunsu.board.post.service.dto.request.PostUpdateServiceRequest;
import jakarta.validation.constraints.Null;

public record PostUpdateRequest(

        @Null(message = "1글자 이상의 제목을 입력해주세요")
        String title,

        @Null(message = "1글자 이상의 내용을 입력해주세요")
        String content
) {

    public PostUpdateServiceRequest toServiceRequest() {
        return PostUpdateServiceRequest.builder()
                .title(title)
                .content(content)
                .build();
    }

}