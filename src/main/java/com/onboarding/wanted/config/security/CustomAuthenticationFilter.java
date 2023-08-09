package com.onboarding.wanted.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onboarding.wanted.config.security.exception.FailLoginRequestException;
import com.onboarding.wanted.member.web.dto.SignInRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    /**
     * 로그인 요청 시
     * CustomAthenticationFilter가 동작하여 AuthenticationToken을 생성하고
     * 이를 AuthenticationManager에게 인증 요청
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SignInRequest loginDto;
        try {
            loginDto = new ObjectMapper().readValue(request.getInputStream(), SignInRequest.class);
        } catch (IOException e) {
            throw new FailLoginRequestException();
        }
        String username = loginDto.getEmail();
        String password = loginDto.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(token);
    }
}
