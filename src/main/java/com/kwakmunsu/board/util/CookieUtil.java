package com.kwakmunsu.board.util;

import jakarta.servlet.http.Cookie;

public final class CookieUtil {

    private CookieUtil() {
    }

    public static Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

}