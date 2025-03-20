package com.kwakmunsu.board.member.infrastruture;


import com.kwakmunsu.board.auth.service.dto.LoginDto;
import com.kwakmunsu.board.global.exception.NotFoundException;
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

    public Member login(LoginDto loginDto) {
        Member member = memberRepository.getMember(loginDto.username());
        validatePassword(loginDto.password(), member.getPassword());
        return member;
    }

    private void validatePassword(String rawPassword, String encryptedPassword) {
        // matches(평문 패스워드, 암호화 패스워드) 순서로 해야 됨.
        if (!bCryptPasswordEncoder.matches(rawPassword, encryptedPassword)) {
            throw new NotFoundException(ErrorCode.BAD_REQUEST_PASSWORD);
        }
    }

    public Member findByRefreshToken(String refreshToken) {
         return memberRepository.findByRefreshToken(refreshToken);
    }

    public Member getMember(Long memberId) {
        return memberRepository.getMember(memberId);
    }

}