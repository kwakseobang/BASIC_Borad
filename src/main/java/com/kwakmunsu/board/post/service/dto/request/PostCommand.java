package com.kwakmunsu.board.post.service.dto.request;

import lombok.Builder;

@Builder
public record PostCommand(String title, String content, Long memberId) {

}