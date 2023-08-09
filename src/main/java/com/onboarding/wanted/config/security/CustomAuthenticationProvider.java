package com.onboarding.wanted.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService domainUserDetailsService;

    /**
     * AuthenticationManager가 넘겨준 Authentication 객체를 통해
     * DB 내 username/password 비교를 통해 인증한다.
     * 이후, 성공시 Authentication 객체 반환
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputEmail = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();

        UserDetails userDetails = domainUserDetailsService.loadUserByUsername(inputEmail);

        if (isNotMatches(inputPassword, userDetails.getPassword())) {
            throw new BadCredentialsException(inputEmail);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    /**
     * AuthenticationProvider에 표시된 Authentication 객체를 지원하는지 여부를 반환
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isNotMatches(String password, String encodedPassword) {
        return !passwordEncoder.matches(password, encodedPassword);
    }
}
