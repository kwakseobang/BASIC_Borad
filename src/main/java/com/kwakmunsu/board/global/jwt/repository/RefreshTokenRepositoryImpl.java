package com.kwakmunsu.board.global.jwt.repository;


import com.kwakmunsu.board.global.jwt.entity.RefreshToken;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public void deleteById(long memberId) {
        refreshTokenJpaRepository.deleteById(memberId);
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenJpaRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_TOKEN));
    }

}