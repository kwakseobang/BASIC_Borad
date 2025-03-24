package com.kwakmunsu.board.auth.service.dto.request;

import lombok.Builder;

@Builder
public record LoginCommand(String username, String password) { }