package com.kwakmunsu.board.favoritespost.service;

import com.kwakmunsu.board.favoritespost.entity.FavoritesPost;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostCommander;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostReader;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesCommand;
import com.kwakmunsu.board.favoritespost.service.dto.FavoritesResponse;
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

    public void append(FavoritesCommand favoritesCommand) {
        Long postId = favoritesCommand.postId();
        postReader.validatePostExist(postId);
        // 해당 유저가 해당 게시물을 저장하지 않았으면 유효성 검증 통과
        favoritesPostReader.validateNotSave(postId, favoritesCommand.memberId());
        favoritesPostCommander.append(postId, favoritesCommand.memberId());
    }

    public FavoritesResponse readAll() {
        List<FavoritesPost> favoritesPosts = favoritesPostReader.readAll();
        List<Post> posts = new ArrayList<>();
        for (FavoritesPost favoritesPost : favoritesPosts) {
            posts.add(postReader.read(favoritesPost.getPostId()));
        }
        return new FavoritesResponse(posts);
    }

    @Transactional
    public void cancel(FavoritesCommand favoritesCommand) {
        Long postId = favoritesCommand.postId();
        postReader.validatePostExist(postId);

        // 해당 유저가 해당 게시물을 저장했으면 유효성 검증 통과
        favoritesPostReader.validateSave(postId, favoritesCommand.memberId());
        favoritesPostCommander.cancel(postId, favoritesCommand.memberId());
    }

}