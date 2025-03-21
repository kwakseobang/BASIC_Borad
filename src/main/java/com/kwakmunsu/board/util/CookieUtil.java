package com.kwakmunsu.board.util;

import static com.kwakmunsu.board.global.jwt.common.TokenExpiration.REFRESH_TOKEN;

import jakarta.servlet.http.Cookie;

public final class CookieUtil {

    private CookieUtil() {
    }

    public static Cookie create(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge((int) REFRESH_TOKEN.getExpirationTime());
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    public static Cookie delete(String key) {
        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        return cookie;
    }

}