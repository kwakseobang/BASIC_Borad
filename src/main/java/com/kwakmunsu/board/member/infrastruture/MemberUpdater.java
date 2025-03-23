package com.kwakmunsu.board.member.infrastruture;

import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.service.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component
public class MemberUpdater {

    private final MemberReader memberReader;
    private final MemberRepository memberRepository;

    @Transactional
    public void updateNickname(Long memberId, String newNickname) {
        memberRepository.validateNickname(newNickname);

        Member member = memberReader.getMember(memberId);
        member.updateNickname(newNickname);
    }

}