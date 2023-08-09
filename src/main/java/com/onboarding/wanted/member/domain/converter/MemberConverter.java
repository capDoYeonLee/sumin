package com.onboarding.wanted.member.domain.converter;

import com.onboarding.wanted.member.entity.MemberEntity;
import com.onboarding.wanted.member.entity.MemberRoleEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberConverter {
    public static MemberEntity to(String email, String password) {
        return MemberEntity.builder()
                .email(email)
                .password(password)
                .role(MemberRoleEntity.ROLE_MEMBER)
                .build();
    }
}
