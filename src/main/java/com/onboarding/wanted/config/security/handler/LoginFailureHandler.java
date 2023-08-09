package com.onboarding.wanted.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onboarding.wanted.common.util.ApiResult;
import com.onboarding.wanted.common.util.ResponseBody;
import com.onboarding.wanted.common.util.ResponseGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        response.setStatus(HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        ApiResult<ResponseBody.FailBody> result = ResponseGenerator.fail(HttpStatus.BAD_REQUEST, "ID 혹은 비밀번호가 일치하지 않습니다");
        new ObjectMapper().writeValue(response.getWriter(), result.getBody());
    }
}
