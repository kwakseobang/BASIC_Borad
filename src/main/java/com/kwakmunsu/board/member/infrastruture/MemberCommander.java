package com.kwakmunsu.board.member.infrastruture;

import com.kwakmunsu.board.auth.service.dto.request.MemberCreateServiceRequest;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.service.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class MemberCommander {

    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public void create(MemberCreateServiceRequest memberCreateServiceRequest) {
        String username = memberCreateServiceRequest.username();
        String nickname = memberCreateServiceRequest.nickname();

        memberRepository.validateUsername(username);
        memberRepository.validateNickname(nickname);

        String encodedPassword = bCryptPasswordEncoder.encode(memberCreateServiceRequest.password());

        Member member = Member.builder()
                .username(username)
                .password(encodedPassword)
                .nickname(nickname)
                .build();

        memberRepository.append(member);
    }

    @Transactional
    public void updateNickname(String newNickname, Long memberId) {
        memberRepository.validateNickname(newNickname);

        Member member = memberRepository.getMember(memberId);
        member.updateNickname(newNickname);
    }

}