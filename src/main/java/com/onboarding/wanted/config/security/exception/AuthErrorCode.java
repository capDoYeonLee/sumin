package com.onboarding.wanted.config.security.exception;

import com.onboarding.wanted.common.errors.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum AuthErrorCode implements ErrorCode {
    FAIL_LOGIN_REQUEST(HttpStatus.BAD_REQUEST, "로그인 요청 필드명이 옳지 않습니다.");
    private final HttpStatus status;
    private final String message;

    AuthErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
