package com.kwakmunsu.board.favoritespost.entity;

import com.kwakmunsu.board.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "favorites_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FavoritesPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_post_id")
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Builder
    public FavoritesPost(Long postId, Long memberId) {
        this.postId = postId;
        this.memberId = memberId;
    }

}