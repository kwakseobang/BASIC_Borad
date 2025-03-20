package com.kwakmunsu.board.member.repository;

import com.kwakmunsu.board.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

}