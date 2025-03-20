package com.kwakmunsu.board.member.repository;


import com.kwakmunsu.board.global.exception.DuplicationException;
import com.kwakmunsu.board.global.exception.NotFoundException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void append(Member member) {
        memberJpaRepository.save(member);
    }


    public Member getMember(String username) {
        return memberJpaRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_MEMBER,
                        String.format("username: %s", username)
                ));
    }

    @Override
    public void validateUsername(String username) {
        if (memberJpaRepository.existsByUsername(username)) {
            throw new DuplicationException(
                    ErrorCode.DUPLICATE_USERNAME,
                    String.format("존재하는 username: %s ", username)
            );
        }
    }

    @Override
    public void validateNickname(String nickname) {
        if (memberJpaRepository.existsByNickname(nickname)) {
            throw new DuplicationException(
                    ErrorCode.DUPLICATE_NICKNAME,
                    String.format("존재하는 nickname: %s ", nickname)
            );
        }
    }

}