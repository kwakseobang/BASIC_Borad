package com.kwakmunsu.board.favoritespost.repository;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.post.entity.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.repository.CursorServiceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FavoritesPostRepositoryImpl implements FavoritesPostRepository {

    private final FavoritesPostJpaRepository favoritesPostJpaRepository;
    private final FavoritesPostQueryDslRepository favoritesPostQueryDslRepository;

    @Override
    public List<PostResponse> findAll(
            CursorServiceRequest request,
            PostSortOption option,
            Long memberId
    ) {
        return favoritesPostQueryDslRepository.findAll(request, option, memberId);
    }

    @Override
    public void append(FavoritesPost favoritesPost) {
        favoritesPostJpaRepository.save(favoritesPost);
    }

    @Override
    public void cancel(Long postId, Long memberId) {
        favoritesPostJpaRepository.deleteByPostIdAndMemberId(postId, memberId);
    }

    @Override
    public boolean isSave(Long postId, Long memberId) {
        return favoritesPostJpaRepository.existsByPostIdAndMemberId(postId, memberId);
    }

}