package com.kwakmunsu.board.likes.service;


import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.likes.infrastruture.LikesUpdater;
import com.kwakmunsu.board.likes.service.dto.LikesCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesUpdater likesUpdater;
    private final LikesReader likesReader;

    public void likePost(LikesCommand likesCommand) {
        // 유저가 게시물의 좋아요가 되어있지 않으면 유효성 검증 통과
        likesReader.validateNotLiked(likesCommand.postId(), likesCommand.memberId());
        likesUpdater.like(likesCommand.postId(), likesCommand.memberId());
    }

    public void unlikePost(LikesCommand likesCommand) {
        // 유저가 게시물의 좋아요가 되어있어야 유효성 검증 통과
        likesReader.validateLiked(likesCommand.postId(), likesCommand.memberId());
        likesUpdater.unLike(likesCommand.postId(), likesCommand.memberId());
    }

}