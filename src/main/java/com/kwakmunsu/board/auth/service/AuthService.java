package com.kwakmunsu.board.auth.service;

import com.kwakmunsu.board.auth.service.dto.request.LoginServiceRequest;
import com.kwakmunsu.board.auth.service.dto.request.MemberCreateServiceRequest;
import com.kwakmunsu.board.global.exception.UnAuthenticationException;
import com.kwakmunsu.board.global.jwt.dto.MemberTokens;
import com.kwakmunsu.board.global.jwt.token.JwtProvider;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.service.MemberCommandService;
import com.kwakmunsu.board.member.service.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtProvider jwtProvider;
    private final MemberCommandService memberCommandService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public void signUp(MemberCreateServiceRequest memberCreateServiceRequest) {
        memberCommandService.create(memberCreateServiceRequest);
    }

    @Transactional
    public MemberTokens login(LoginServiceRequest request) {
        Member member = findMember(request.username(), request.password());
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());

        member.updateRefreshToken(memberTokens.refreshToken());
        return memberTokens;
    }

    public void logout(Long memberId) {
        Member member = memberRepository.getMember(memberId);
        member.updateRefreshToken(null); // 로그아웃 시 삭제해주기 위해 null 사용
    }

    @Transactional
    public MemberTokens reissue(String reissueToken) {
        if (jwtProvider.isNotValidateToken(reissueToken)) {
            throw new UnAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
        Member member = memberRepository.findByRefreshToken(reissueToken);
        MemberTokens memberTokens = jwtProvider.createTokens(member.getId(), member.getRole());

        member.updateRefreshToken(memberTokens.refreshToken());
        return memberTokens;
    }

    private Member findMember(String username, String password) {
        Member member = memberRepository.getMember(username);
        validatePassword(password, member.getPassword());
        return member;
    }

    private void validatePassword(String rawPassword, String encryptedPassword) {
        // matches(평문 패스워드, 암호화 패스워드) 순서로 해야 됨.
        if (!bCryptPasswordEncoder.matches(rawPassword, encryptedPassword)) {
            throw new UnAuthenticationException(ErrorCode.UNAUTHORIZED_ERROR);
        }
    }

}