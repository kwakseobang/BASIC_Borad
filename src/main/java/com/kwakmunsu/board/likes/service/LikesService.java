package com.kwakmunsu.board.likes.service;


import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.likes.infrastruture.LikesCommander;
import com.kwakmunsu.board.likes.service.dto.LikesCommand;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesCommander likesCommander;
    private final LikesReader likesReader;
    private final PostReader postReader;

    public void likePost(LikesCommand likesCommand) {
        Long postId = likesCommand.postId();
        postReader.validatePostExist(postId);

        // 유저가 게시물의 좋아요가 되어있지 않으면 유효성 검증 통과
        likesReader.validateNotLiked(postId, likesCommand.memberId());
        likesCommander.like(postId, likesCommand.memberId());
    }

    public void unlikePost(LikesCommand likesCommand) {
        Long postId = likesCommand.postId();
        postReader.validatePostExist(postId);

        // 유저가 게시물의 좋아요가 되어있어야 유효성 검증 통과
        likesReader.validateLiked(postId, likesCommand.memberId());
        likesCommander.unLike(postId, likesCommand.memberId());
    }

}