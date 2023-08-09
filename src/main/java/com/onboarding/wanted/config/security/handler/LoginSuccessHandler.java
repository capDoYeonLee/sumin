package com.onboarding.wanted.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onboarding.wanted.common.util.ResponseBody;
import com.onboarding.wanted.config.security.CustomTokenProvider;
import com.onboarding.wanted.config.security.CustomUserDetail;
import com.onboarding.wanted.member.domain.service.CreateTokenUsecase;
import com.onboarding.wanted.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.onboarding.wanted.config.security.JwtConstants.*;
import static com.onboarding.wanted.config.security.JwtConstants.REFRESH_TOKEN;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final CustomTokenProvider tokenProvider;
    private final CreateTokenUsecase tokenUsecase;

    /**
     * 로그인 성공시 CustomSucessHandler가 동작
     * 즉, AT 및 RT 생성 후 response에 반환
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomUserDetail member = (CustomUserDetail) authentication.getPrincipal();

        String accessToken = tokenProvider.generateAccessToken(member);
        String refreshToken = tokenProvider.generateRefreshToken(member);

        tokenUsecase.execute(member.getUsername(), refreshToken);

        Cookie cookie = CookieUtil.createCookie(REFRESH_TOKEN, refreshToken);

        response.addHeader(HttpHeaders.AUTHORIZATION, BEARER + accessToken);
        response.addCookie(cookie);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseBody.SuccessBodyWithoutData body = new ResponseBody.SuccessBodyWithoutData(HttpStatus.OK.toString());
        ObjectMapper om = new ObjectMapper();
        String responseBody = om.writeValueAsString(body);
        response.getWriter().println(responseBody);
    }
}
