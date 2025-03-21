package com.kwakmunsu.board.post.service.dto.response;

import com.kwakmunsu.board.post.entity.Post;
import lombok.Builder;

@Builder
public record PostResponse(
        Long id,
        Long writerId,
        String title,
        String content
) {

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .writerId(post.getWriterId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

}