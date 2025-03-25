package com.kwakmunsu.board.member.service;

import static com.kwakmunsu.board.member.entity.Member.createMember;

import com.kwakmunsu.board.auth.service.dto.request.MemberCreateServiceRequest;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.service.dto.NicknameCreateServiceRequest;
import com.kwakmunsu.board.member.service.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberCommandService {

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
    public void updateNickname(Long memberId, NicknameCreateServiceRequest request) {
        memberRepository.validateNickname(request.nickname());

        Member member = memberRepository.getMember(memberId);
        member.updateNickname(request.nickname());
    }

}