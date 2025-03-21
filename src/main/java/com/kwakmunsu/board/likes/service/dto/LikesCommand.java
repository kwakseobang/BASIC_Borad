package com.kwakmunsu.board.likes.service.dto;

public record LikesCommand(Long postId, Long memberId) { }