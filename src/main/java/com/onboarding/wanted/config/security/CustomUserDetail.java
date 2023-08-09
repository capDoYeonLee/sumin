package com.onboarding.wanted.config.security;

import com.onboarding.wanted.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetail implements UserDetails {
    private final transient MemberEntity member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(member.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    /**
     *
     * @return 계정 만료 상태(true : 만료되지 않음)
     */

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     *
     * @return 계정 잠금 상태(true : 잠기지 않음)
     */

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     *
     * @return 계정 활성화(사용 가능) 상태 (true : 활성화)
     */

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     *
     * @return 계정의 권한 목록
     */

    @Override
    public boolean isEnabled() {
        return false;
    }
}
