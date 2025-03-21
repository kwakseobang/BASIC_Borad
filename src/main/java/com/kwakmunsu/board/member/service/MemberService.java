package com.kwakmunsu.board.member.service;

import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.infrastruture.MemberReader;
import com.kwakmunsu.board.member.infrastruture.MemberUpdater;
import com.kwakmunsu.board.member.service.dto.NicknameCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberUpdater memberUpdater;
    private final MemberReader memberReader;

    public void updateNickname(NicknameCreateCommand nicknameCreateCommand) {
        Long memberId = nicknameCreateCommand.memberId();
        String nickname = nicknameCreateCommand.nickname();
        memberUpdater.updateNickname(memberId, nickname);
    }

    public void logout(Long memberId) {
        Member member = memberReader.getMember(memberId);
        member.updateRefreshToken(null); // 로그아웃 시 삭제해주기 위해 null 사용
    }

}