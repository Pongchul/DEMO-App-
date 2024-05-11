package com.DEMO.backend.member.presentation;

import com.DEMO.backend.member.application.MemberService;
import com.DEMO.backend.member.application.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public ResponseEntity<Void> signUp(SignUpRequest request) {
        memberService.signUp(request);

        return ResponseEntity.ok().build();
    }
}
