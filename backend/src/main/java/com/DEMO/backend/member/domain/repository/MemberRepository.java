package com.DEMO.backend.member.domain.repository;

import com.DEMO.backend.member.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository {

    boolean existsByEmail(Email email);

    boolean existsByNickname(String nickname);

}
