package com.kwakmunsu.board.member.repository;

import com.kwakmunsu.board.member.entity.Member;

public interface MemberRepository {

    void append(Member member);
    Member getMember(String username);
    Member getMember(Long memberId);
    Member findByRefreshToken(String refreshToken);
    void validateUsername(String username);
    void validateNickname(String nickname);


}