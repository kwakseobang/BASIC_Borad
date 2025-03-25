package com.kwakmunsu.board.member.service;

import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.infrastruture.MemberCommander;
import com.kwakmunsu.board.member.infrastruture.MemberReader;
import com.kwakmunsu.board.member.service.dto.NicknameCreateServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberCommander memberCommander;
    private final MemberReader memberReader;

    public void updateNickname(Long memberId, NicknameCreateServiceRequest request) {
        String nickname = request.nickname();
        memberCommander.updateNickname(nickname, memberId);
    }

    public void logout(Long memberId) {
        Member member = memberReader.getMember(memberId);
        member.updateRefreshToken(null); // 로그아웃 시 삭제해주기 위해 null 사용
    }

}