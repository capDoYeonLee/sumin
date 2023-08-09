package com.onboarding.wanted.member.domain.exception;

import com.onboarding.wanted.common.errors.exception.BusinessException;
import com.onboarding.wanted.common.errors.exception.ErrorCode;

public class DuplicatedEmailException extends BusinessException {
    public DuplicatedEmailException( ){
        super(MemberErrorCode.DUPLICATED_EMAIL);
    }
}
