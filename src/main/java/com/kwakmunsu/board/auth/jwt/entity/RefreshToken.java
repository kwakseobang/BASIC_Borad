package com.kwakmunsu.board.auth.jwt.entity;

import com.kwakmunsu.board.member.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public RefreshToken(String refreshToken, Long memberId, Role role) {
        this.refreshToken = refreshToken;
        this.memberId = memberId;
        this.role = role;
    }

    public boolean isEqualToRefreshToken(String targetToken) {
        return refreshToken.equals(targetToken);
    }

}