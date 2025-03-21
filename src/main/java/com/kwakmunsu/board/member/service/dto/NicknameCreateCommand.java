package com.kwakmunsu.board.member.service.dto;

import lombok.Builder;

@Builder
public record NicknameCreateCommand(String nickname, Long memberId) { }