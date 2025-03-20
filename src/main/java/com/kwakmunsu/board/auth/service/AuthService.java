package com.kwakmunsu.board.auth.service;

import com.kwakmunsu.board.auth.service.dto.NewMember;
import com.kwakmunsu.board.member.infrastruture.MemberAppender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberAppender memberAppender;

    public void signUp(NewMember newMember) {
            memberAppender.create(newMember);
    }
}