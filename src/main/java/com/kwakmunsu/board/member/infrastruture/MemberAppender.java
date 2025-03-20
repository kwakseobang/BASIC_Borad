package com.kwakmunsu.board.member.infrastruture;


import com.kwakmunsu.board.auth.service.dto.NewMember;
import com.kwakmunsu.board.global.exception.DuplicationException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.member.entity.Member;
import com.kwakmunsu.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberAppender {

    private final MemberRepository memberRepository;

    public void create(NewMember newMember) {
        validateUsername(newMember.username());
        validateNickname(newMember.nickname());

        Member member = Member.builder()
                .username(newMember.username())
                .password(newMember.password()) // TODO: 암호화 해서 넣을 예정
                .nickname(newMember.nickname())
                .build();
        memberRepository.append(member);

    }

    private void validateUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new DuplicationException(
                    ErrorCode.DUPLICATE_USERNAME,
                    String.format("존재하는 username: %s ", username)
            );
        }
    }

    private void validateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new DuplicationException(
                    ErrorCode.DUPLICATE_NICKNAME,
                    String.format("존재하는 nickname: %s ", nickname)
            );
        }
    }

}