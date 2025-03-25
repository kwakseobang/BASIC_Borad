package com.kwakmunsu.board.favoritespost.service;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesPreviewResponse;
import com.kwakmunsu.board.favoritespost.service.repository.FavoritesPostRepository;
import com.kwakmunsu.board.likes.service.LikesQueryService;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.service.PostQueryService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FavoritesQueryService {

    private final PostQueryService postQueryService;
    private final FavoritesPostRepository favoritesPostRepository;
    private final LikesQueryService likesQueryService;

    public List<FavoritesPreviewResponse> readAll() {
        List<FavoritesPost> favoritesPosts = favoritesPostRepository.readAll();
        List<FavoritesPreviewResponse> favoritesPreviewResponses = new ArrayList<>();

        for (FavoritesPost favoritesPost : favoritesPosts) {
            Post post = postQueryService.readById(favoritesPost.getPostId());
            long likesCount = likesQueryService.readLikeCount(post.getId());
            long favoritesCount = favoritesPostRepository.countByPostId(post.getId());
            favoritesPreviewResponses.add(
                    FavoritesPreviewResponse.from(post, likesCount, favoritesCount)
            );
        }
        return favoritesPreviewResponses;
    }

    public long countByPostId(Long postId) {
        return favoritesPostRepository.countByPostId(postId);
    }

}