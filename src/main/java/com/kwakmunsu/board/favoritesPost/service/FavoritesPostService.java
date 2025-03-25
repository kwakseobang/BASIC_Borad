package com.kwakmunsu.board.favoritespost.service;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostCommander;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostReader;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesPreviewResponse;
import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FavoritesPostService {

    private final FavoritesPostCommander favoritesPostCommander;
    private final FavoritesPostReader favoritesPostReader;
    private final PostReader postReader;
    private final LikesReader likesReader;

    public void append(Long postId, Long memberId) {
        postReader.validatePostExist(postId);

        // 해당 유저가 해당 게시물을 저장하지 않았으면 유효성 검증 통과
        favoritesPostReader.validateNotSave(postId, memberId);
        favoritesPostCommander.append(postId, memberId);
    }

    public List<FavoritesPreviewResponse> readAll() {
        List<FavoritesPost> favoritesPosts = favoritesPostReader.readAll();
        List<FavoritesPreviewResponse> favoritesPreviewResponses = new ArrayList<>();

        for (FavoritesPost favoritesPost : favoritesPosts) {
            Post post = postReader.read(favoritesPost.getPostId());
            long likesCount = likesReader.readLikeCount(post.getId());
            long favoritesCount = favoritesPostReader.countByPostId(post.getId());
            favoritesPreviewResponses.add(
                    FavoritesPreviewResponse.from(post, likesCount, favoritesCount)
            );
        }
        return favoritesPreviewResponses;
    }

    @Transactional
    public void cancel(Long postId, Long memberId) {
        postReader.validatePostExist(postId);

        // 해당 유저가 해당 게시물을 저장했으면 유효성 검증 통과
        favoritesPostReader.validateSave(postId, memberId);
        favoritesPostCommander.cancel(postId, memberId);
    }

}