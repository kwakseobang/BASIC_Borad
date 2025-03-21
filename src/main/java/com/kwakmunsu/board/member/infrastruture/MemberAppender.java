package com.kwakmunsu.board.member.infrastruture;


import com.kwakmunsu.board.auth.service.dto.MemberCreateCommand;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberAppender {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void create(MemberCreateCommand memberCreateCommand) {
        String username = memberCreateCommand.username();
        String nickname = memberCreateCommand.nickname();
        memberRepository.validateUsername(username);
        memberRepository.validateNickname(nickname);

        String encodedPassword = bCryptPasswordEncoder.encode(memberCreateCommand.password());
        Member member = Member.builder()
                .username(username)
                .password(encodedPassword)
                .nickname(nickname)
                .build();
        memberRepository.append(member);
    }

}