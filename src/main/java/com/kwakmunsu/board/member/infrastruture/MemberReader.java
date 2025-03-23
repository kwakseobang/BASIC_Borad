package com.kwakmunsu.board.member.infrastruture;


import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.exception.UnAuthenticationException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberReader {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member login(String username, String password) {
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

    public Member findByRefreshToken(String refreshToken) {
         return memberRepository.findByRefreshToken(refreshToken);
    }

    public Member getMember(Long memberId) {
        return memberRepository.getMember(memberId);
    }

}