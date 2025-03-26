package com.kwakmunsu.board.favoritespost.service.repository;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.post.entity.dto.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.service.dto.request.CursorServiceRequest;
import java.util.List;

public interface FavoritesPostRepository {

    List<PostResponse> findAll(CursorServiceRequest request, PostSortOption option, Long memberId);
    void append(FavoritesPost favoritesPost);
    void cancel(Long postId, Long memberId);
    boolean isSave(Long postId, Long memberId);

}