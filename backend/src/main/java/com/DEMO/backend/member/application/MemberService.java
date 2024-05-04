package com.DEMO.backend.member.application;

import com.DEMO.backend.global.exception.BadRequestException;
import com.DEMO.backend.global.exception.ExceptionCode;
import com.DEMO.backend.member.application.dto.SignUpRequest;
import com.DEMO.backend.member.domain.Email;
import com.DEMO.backend.member.domain.Member;
import com.DEMO.backend.member.domain.Password;
import com.DEMO.backend.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(SignUpRequest request) {
        Email email = Email.from(request.email());
        Password password = Password.encrypt(request.password(), passwordEncoder);
        String nickname = request.nickname();

        validateEmailIsNotDuplicated(email);
        validateNicknameIsNotDuplicated(nickname);

        Member member = new Member(email, password, nickname);

        memberRepository.save(member);
    }


    private void validateEmailIsNotDuplicated(Email email) {
        if (memberRepository.existsByEmail(email)) {
            throw new BadRequestException(ExceptionCode.SIGNUP_EMAIL_DUPLICATED);
        }
    }

    private void validateNicknameIsNotDuplicated(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new BadRequestException(ExceptionCode.SIGNUP_NICKNAME_DUPLICATED);
        }
    }

}