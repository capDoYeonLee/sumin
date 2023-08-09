package com.onboarding.wanted.config.security.exception;

import com.onboarding.wanted.common.errors.exception.BusinessException;


public final class FailLoginRequestException extends BusinessException {
    public FailLoginRequestException() {
        super(AuthErrorCode.FAIL_LOGIN_REQUEST);
    }
}
