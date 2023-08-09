package com.onboarding.wanted.member.domain.exception;

import com.onboarding.wanted.common.errors.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST,"이메일 중복입니다.");
    ;

    private final HttpStatus status;
    private final String message;

    MemberErrorCode(HttpStatus status, String message) {
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
