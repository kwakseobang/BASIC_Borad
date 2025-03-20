package com.kwakmunsu.board.member.repository;

import com.kwakmunsu.board.member.entity.Member;

public interface MemberRepository {

    void append(Member member);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

}