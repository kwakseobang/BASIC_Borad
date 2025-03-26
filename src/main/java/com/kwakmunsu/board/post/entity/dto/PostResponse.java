package com.kwakmunsu.board.post.entity.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostResponse(
        Long postId,
        String title,
        String writer,
        LocalDateTime createAt,
        Long viewCount,
        Long likeCount,
        Long favoritesCount
) {

}