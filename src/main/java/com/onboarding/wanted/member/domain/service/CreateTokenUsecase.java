package com.onboarding.wanted.member.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateTokenUsecase {
    private final MemberRepository memberRepository;

    @Transactional
    public void execute(String email, String refreshToken) {
        memberRepository.findByEmail(email)
                .ifPresent(memberEntity -> memberEntity.updateRefreshToken(refreshToken));
    }
}
