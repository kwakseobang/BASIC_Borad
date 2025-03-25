package com.kwakmunsu.board.likes.service;


import com.kwakmunsu.board.likes.infrastruture.LikesCommander;
import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesCommander likesCommander;
    private final LikesReader likesReader;
    private final PostReader postReader;

    public void likePost(Long postId, Long memberId) {
        postReader.validatePostExist(postId);

        // 유저가 게시물의 좋아요가 되어있지 않으면 유효성 검증 통과
        likesReader.validateNotLiked(postId, memberId);
        likesCommander.incrementLikes(postId, memberId);
    }

    public void unlikePost(Long postId, Long memberId) {
        postReader.validatePostExist(postId);

        // 유저가 게시물의 좋아요가 되어있어야 유효성 검증 통과
        likesReader.validateLiked(postId, memberId);
        likesCommander.unLike(postId, memberId);
    }

}