package com.kwakmunsu.board.member.repository;


import com.kwakmunsu.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void append(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public boolean existsByUsername(String username) {
        return memberJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return memberJpaRepository.existsByNickname(nickname);
    }

}