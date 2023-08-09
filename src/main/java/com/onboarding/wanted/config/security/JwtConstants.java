package com.onboarding.wanted.config.security;
public final class JwtConstants {
    public static final Long ACCESS_TOKEN_VALIDATION_SECOND = 1800L * 1000;
    public static final Long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;

    public static final String BEARER = "Bearer ";
    public static final String REFRESH_TOKEN = "refreshToken";

    public static final String SIGNUP_URL = "api/signup";
    public static final String LOGIN_URL = "/api/login";
    public static final String REFRESH_URL = "/api/refresh";
}
