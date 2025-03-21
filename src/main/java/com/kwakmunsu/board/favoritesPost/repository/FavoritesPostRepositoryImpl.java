package com.kwakmunsu.board.favoritespost.repository;


import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.post.entity.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FavoritesPostRepositoryImpl implements FavoritesPostRepository {

    private final FavoritesPostJpaRepository favoritesPostJpaRepository;

    @Override
    public List<FavoritesPost> readAll() {
        return favoritesPostJpaRepository.findAll();
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