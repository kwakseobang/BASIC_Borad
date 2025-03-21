package com.kwakmunsu.board.likes.repository;

import com.kwakmunsu.board.likes.entity.Likes;

public interface LikesRepository {

     long read(Long postId);
     void incrementLikes(Likes likes);
     void decrementLikes(Long postId, Long memberId);
     boolean isLike(Long postId, Long memberId);

}