package com.onboarding.wanted.util;

import lombok.experimental.UtilityClass;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.onboarding.wanted.config.security.JwtConstants.*;

@UtilityClass
public class CookieUtil {
    public Cookie createCookie(String cookieName, String value){
        Cookie token = new Cookie(cookieName, value);
        token.setHttpOnly(true);
        token.setMaxAge(Math.toIntExact(REFRESH_TOKEN_VALIDATION_SECOND));
        token.setPath("/");
        return token;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName){
        final Cookie[] cookies = req.getCookies();
        if(cookies == null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }
}
