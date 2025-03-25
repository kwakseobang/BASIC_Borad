package com.kwakmunsu.board.auth.service;

import com.kwakmunsu.board.auth.service.dto.request.LoginServiceRequest;
import com.kwakmunsu.board.auth.service.dto.request.MemberCreateServiceRequest;
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

    public void signUp(MemberCreateServiceRequest memberCreateServiceRequest) {
        memberCommander.create(memberCreateServiceRequest);
    }

    @Transactional
    public MemberTokens login(LoginServiceRequest request) {
        Member member = memberReader.login(request.username(), request.password());
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());

        member.updateRefreshToken(memberTokens.refreshToken());
        return memberTokens;
    }

    @Transactional
    public MemberTokens reissue(String reissueToken) {
        if (jwtProvider.isNotValidateToken(reissueToken)) {
            throw new UnAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
        Member member = memberReader.findByRefreshToken(reissueToken);
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());

        member.updateRefreshToken(memberTokens.refreshToken());
        return memberTokens;
    }

}