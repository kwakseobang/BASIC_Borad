package com.kwakmunsu.board.member.infrastruture;

import static com.kwakmunsu.board.member.entity.Member.createMember;

import com.kwakmunsu.board.auth.service.dto.request.MemberCreateServiceRequest;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.service.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class MemberCommander {

    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public void create(MemberCreateServiceRequest request) {
        String username = request.username();
        String nickname = request.nickname();

        memberRepository.validateUsername(username);
        memberRepository.validateNickname(nickname);

        String encodedPassword = bCryptPasswordEncoder.encode(request.password());

        Member member = createMember(username,encodedPassword,nickname);
        memberRepository.append(member);
    }

    @Transactional
    public void updateNickname(String newNickname, Long memberId) {
        memberRepository.validateNickname(newNickname);

        Member member = memberRepository.getMember(memberId);
        member.updateNickname(newNickname);
    }

}