package com.kwakmunsu.board.favoritespost.infrastruture;


import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.repository.FavoritesPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class FavoritesPostUpdater {

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
        favoritesPostRepository.cancel(postId, memberId);
    }
}