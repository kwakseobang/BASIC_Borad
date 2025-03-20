package com.kwakmunsu.board.member.service;

import com.kwakmunsu.board.member.infrastruture.MemberUpdater;
import com.kwakmunsu.board.member.service.dto.NewNicknameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberUpdater memberUpdater;

    public void updateNickname(Long memberId, NewNicknameDto newNicknameDto) {
        memberUpdater.updateNickname(memberId,newNicknameDto.nickname());
    }

}