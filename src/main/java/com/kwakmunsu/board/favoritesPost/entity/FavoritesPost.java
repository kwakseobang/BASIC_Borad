package com.kwakmunsu.board.favoritesPost.entity;

import com.kwakmunsu.board.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "favorites_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FavoritesPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_post_id")
    private Long id;

}