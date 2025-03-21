package com.kwakmunsu.board.auth.service.dto;

import lombok.Builder;

@Builder
public record LoginCommand(String username, String password) {

}