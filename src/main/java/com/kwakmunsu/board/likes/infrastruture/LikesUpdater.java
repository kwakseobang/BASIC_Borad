package com.kwakmunsu.board.likes.infrastruture;

import com.kwakmunsu.board.likes.entity.Likes;
import com.kwakmunsu.board.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class LikesUpdater {

    private final LikesRepository likesRepository;

    public void like(Long postId, Long memberId) {
        Likes like = Likes.builder()
                .postId(postId)
                .memberId(memberId)
                .build();
        likesRepository.incrementLikes(like);

    }

    @Transactional
    public void unLike(Long postId, Long memberId) {
        likesRepository.decrementLikes(postId, memberId);
    }

}