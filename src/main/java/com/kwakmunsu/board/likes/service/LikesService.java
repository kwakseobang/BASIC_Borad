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
        // 좋아요가 안되어 있어야지 유효한 검증이다.
        likesReader.validateNotLiked(likesCommand.postId(), likesCommand.memberId());
        likesUpdater.like(likesCommand.postId(), likesCommand.memberId());
    }

    public void unlikePost(LikesCommand likesCommand) {
        // 좋아요가 되어 있어야지 유효한 검증이다.
        likesReader.validateLiked(likesCommand.postId(), likesCommand.memberId());
        likesUpdater.unLike(likesCommand.postId(), likesCommand.memberId());
    }

}