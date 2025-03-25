package com.kwakmunsu.board.likes.service.repository;

import com.kwakmunsu.board.likes.entity.Likes;

public interface LikesRepository {

     long countByPostId(Long postId);
     void incrementLikes(Likes likes);
     void decrementLikes(Long postId, Long memberId);
     boolean isLike(Long postId, Long memberId);

}