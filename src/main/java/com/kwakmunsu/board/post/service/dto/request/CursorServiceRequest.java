package com.kwakmunsu.board.post.service.dto.request;

import java.time.LocalDateTime;

public record CursorServiceRequest(
        Long lastId,
        Long lastViews,
        String lastTitle,
        LocalDateTime lastCreatedAt
) {

}