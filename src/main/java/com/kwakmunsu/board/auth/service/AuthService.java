package com.kwakmunsu.board.auth.service;

import com.kwakmunsu.board.auth.service.dto.LoginDto;
import com.kwakmunsu.board.auth.service.dto.NewMemberDto;
import com.kwakmunsu.board.global.jwt.dto.MemberTokens;
import com.kwakmunsu.board.global.jwt.token.JwtProvider;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.infrastruture.MemberAppender;
import com.kwakmunsu.board.member.infrastruture.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberAppender memberAppender;
    private final MemberValidator memberValidator;
    private final JwtProvider jwtProvider;

    public void signUp(NewMemberDto newMemberDto) {
        memberAppender.create(newMemberDto);
    }

    public MemberTokens login(LoginDto loginDto) {
        Member member = memberValidator.login(loginDto);
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());
        member.updateRefreshToken(member.getRefreshToken());
        return memberTokens;
    }


}