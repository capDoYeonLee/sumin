package com.onboarding.wanted.member.domain.service;

import com.onboarding.wanted.member.domain.exception.DuplicatedEmailException;
import com.onboarding.wanted.member.domain.converter.MemberConverter;
import com.onboarding.wanted.member.entity.MemberEntity;
import com.onboarding.wanted.member.web.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpUsecase {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String execute(SignUpRequest signUpRequest) {
        isDuplicatedEmail(signUpRequest.getEmail());

        MemberEntity memberEntity = MemberConverter.to(signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));
        MemberEntity save = memberRepository.save(memberEntity);
        return save.getEmail();
    }

    private void isDuplicatedEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicatedEmailException();
        }
    }
}
