package com.kwakmunsu.board.util;

import com.kwakmunsu.board.global.exception.UnAuthenticationException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class JwtUtil {

    private JwtUtil() {
    }

    public static Long getCurrentMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new UnAuthenticationException(
                    ErrorCode.UNAUTHORIZED_ERROR,
                    "Security Context 에 인증 정보가 없습니다.");
        }
        return Long.parseLong(authentication.getName());
    }

}