package com.kwakmunsu.board.auth.service;

import com.kwakmunsu.board.auth.service.dto.LoginCommand;
import com.kwakmunsu.board.auth.service.dto.MemberCreateCommand;
import com.kwakmunsu.board.auth.service.dto.ReissueTokenCommand;
import com.kwakmunsu.board.global.exception.UnAuthenticationException;
import com.kwakmunsu.board.global.jwt.dto.MemberTokens;
import com.kwakmunsu.board.global.jwt.token.JwtProvider;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.infrastruture.MemberCommander;
import com.kwakmunsu.board.member.infrastruture.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberCommander memberCommander;
    private final MemberReader memberReader;
    private final JwtProvider jwtProvider;

    public void signUp(MemberCreateCommand memberCreateCommand) {
        memberCommander.create(memberCreateCommand);
    }

    @Transactional
    public MemberTokens login(LoginCommand loginCommand) {
        Member member = memberReader.login(loginCommand.username(),loginCommand.password());
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());
        member.updateRefreshToken(memberTokens.refreshToken());

        return memberTokens;
    }
    // TODO: 로직 분리 할 수 있을 거 같다. 좀 더 고민해보자.
    @Transactional
    public MemberTokens reissue(ReissueTokenCommand reissueTokenCommand) {
        if (jwtProvider.isNotValidateToken(reissueTokenCommand.reissueToken())) {
            throw new UnAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
        Member member = memberReader.findByRefreshToken(reissueTokenCommand.reissueToken());
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());
        member.updateRefreshToken(memberTokens.refreshToken());

        return memberTokens;
    }

}