package com.kwakmunsu.board.favoritespost.service.repository;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.post.entity.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.repository.CursorServiceRequest;
import java.util.List;

public interface FavoritesPostRepository {

    List<PostResponse> findAll(CursorServiceRequest request, PostSortOption option, Long memberId);
    void append(FavoritesPost favoritesPost);
    void cancel(Long postId, Long memberId);
    boolean isSave(Long postId, Long memberId);

}