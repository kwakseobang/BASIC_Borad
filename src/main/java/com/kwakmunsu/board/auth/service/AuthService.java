package com.kwakmunsu.board.auth.service;

import com.kwakmunsu.board.auth.service.dto.LoginDto;
import com.kwakmunsu.board.auth.service.dto.NewMemberDto;
import com.kwakmunsu.board.auth.service.dto.ReissueTokenDto;
import com.kwakmunsu.board.global.exception.UnAuthenticationException;
import com.kwakmunsu.board.global.jwt.dto.MemberTokens;
import com.kwakmunsu.board.global.jwt.token.JwtProvider;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.infrastruture.MemberAppender;
import com.kwakmunsu.board.member.infrastruture.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberAppender memberAppender;
    private final MemberReader memberReader;
    private final JwtProvider jwtProvider;

    public void signUp(NewMemberDto newMemberDto) {
        memberAppender.create(newMemberDto);
    }

    @Transactional
    public MemberTokens login(LoginDto loginDto) {
        Member member = memberReader.login(loginDto);
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());
        member.updateRefreshToken(memberTokens.refreshToken());
        return memberTokens;
    }
    // TODO: 로직 분리 할 수 있을 거 같다. 좀 더 고민해보자.
    @Transactional
    public MemberTokens reissue(ReissueTokenDto reissueTokenDto) {
        if (!jwtProvider.validateToken(reissueTokenDto.reissueToken())) {
            throw new UnAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
        Member member = memberReader.findByRefreshToken(reissueTokenDto.reissueToken());
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());
        member.updateRefreshToken(memberTokens.refreshToken());
        return memberTokens;
    }

}