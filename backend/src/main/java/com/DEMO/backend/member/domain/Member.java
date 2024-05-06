package com.DEMO.backend.member.domain;

import com.DEMO.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 25)
    private String nickname;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Enumerated
    private MemberState memberState;

    public Member(Email email, Password password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }


}
