package com.kwakmunsu.board.post.repository;

import java.time.LocalDateTime;

public record CursorServiceRequest(
        Long lastId,
        Long lastViews,
        String lastTitle,
        LocalDateTime lastCreatedAt
) {

}