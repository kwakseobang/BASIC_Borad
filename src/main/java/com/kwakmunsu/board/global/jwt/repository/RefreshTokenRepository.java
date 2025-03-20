package com.kwakmunsu.board.global.jwt.repository;

import com.kwakmunsu.board.global.jwt.entity.RefreshToken;

public interface RefreshTokenRepository {

     void save(RefreshToken refreshToken);
     void deleteById(long memberId);
     RefreshToken findByRefreshToken(String refreshToken);

}