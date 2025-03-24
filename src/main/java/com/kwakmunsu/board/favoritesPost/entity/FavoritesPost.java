package com.kwakmunsu.board.favoritespost.entity;

import com.kwakmunsu.board.global.entity.BaseEntity;
import com.kwakmunsu.board.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;


@Table(name = "favorites_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FavoritesPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_post_id")
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public FavoritesPost(Long postId, Member member) {
        this.postId = postId;
        this.member = member;
    }

}