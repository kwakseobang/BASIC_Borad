package com.kwakmunsu.board.favoritespost.infrastruture;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class FavoritesPostCommander {

    private final FavoritesPostRepository favoritesPostRepository;

    public void append(Long postId, Long memberId) {
        FavoritesPost favoritesPost = FavoritesPost.builder()
                .postId(postId)
                .memberId(memberId)
                .build();

        favoritesPostRepository.append(favoritesPost);
    }

    @Transactional
    public void cancel(Long postId, Long memberId) {
        if (favoritesPostRepository.isSave(postId, memberId)) {
            favoritesPostRepository.cancel(postId, memberId);
        }
    }

}