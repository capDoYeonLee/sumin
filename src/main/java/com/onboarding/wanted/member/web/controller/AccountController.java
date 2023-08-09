package com.onboarding.wanted.member.web.controller;

import com.onboarding.wanted.common.util.ResponseGenerator;
import com.onboarding.wanted.member.domain.service.SignUpUsecase;
import com.onboarding.wanted.member.web.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AccountController {
    private final SignUpUsecase signUpUsecase;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignUpRequest request) {
        return ResponseGenerator.success(signUpUsecase.execute(request), HttpStatus.OK);
    }

}
