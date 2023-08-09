package com.onboarding.wanted.member.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
public class SignInRequest {
    @Email
    private String email;
    @Min(value = 8)
    private String password;

    @Builder
    private SignInRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }
}
