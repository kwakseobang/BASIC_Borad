package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.comment.entity.Comment;
import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.comment.infrastruture.CommentRemover;
import com.kwakmunsu.board.favoritespost.infrastruture.FavoritesPostUpdater;
import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.likes.infrastruture.LikesUpdater;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostAppender;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import com.kwakmunsu.board.post.infrastruture.PostRemover;
import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.post.service.dto.request.PostDeleteCommand;
import com.kwakmunsu.board.post.service.dto.request.PostPageableCommand;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
import com.kwakmunsu.board.post.service.dto.response.PostPageResponse;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import com.kwakmunsu.board.post.service.dto.response.PostViewsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    //TODO: 이 상태면 의존성이 많아짐.. 근데 내가 하는 방식이면 이렇게 짜야됨 어케 해야 할까..
    private final PostAppender postAppender;
    private final PostReader postReader;
    private final PostRemover postRemover;
    private final LikesReader likesReader;
    private final LikesUpdater likesUpdater;
    private final FavoritesPostUpdater favoritesPostUpdater;
    private final CommentReader commentReader;
    private final CommentRemover commentRemover;

    public void create(PostCreateCommand postCreateCommand) {
        postAppender.append(
                postCreateCommand.title(),
                postCreateCommand.content(),
                postCreateCommand.memberId()
        );
    }

    public PostResponse read(Long postId) {
        Post post = postReader.read(postId);
        long likesCount = likesReader.readLikes(postId);
        List<Comment> comments = commentReader.readByPostId(postId);

        return PostResponse.from(post, likesCount, comments);
    }

    public PostPageResponse readAll(PostPageableCommand pageableCommand) {
        Pageable pageable = PageRequest.of(
                pageableCommand.page() - 1,
                pageableCommand.pageSize(),
                Sort.by(Direction.DESC, pageableCommand.sortBy())
        );

        return new PostPageResponse(postReader.readAll(pageable));
    }

    public void update(PostUpdateCommand postUpdateCommand) {
        Post post = postReader.read(postUpdateCommand.postId());
        post.updatePost(postUpdateCommand.title(), postUpdateCommand.content());
    }

    public void updateViews(Long postId) {
        Post post = postReader.read(postId);
        post.incrementViewCount();
    }

    public PostViewsResponse readViews(Long postId) {
        Post post = postReader.read(postId);

        return new PostViewsResponse(post.getViewCount());
    }

    public void delete(PostDeleteCommand postDeleteCommand) {
        Long postId = postDeleteCommand.postId();
        Long memberId = postDeleteCommand.memberId();

        postReader.validatePostExist(postId);
        likesUpdater.unLike(postId, memberId);
        commentRemover.deleteAll(postId);
        favoritesPostUpdater.cancel(postId, memberId);
        postRemover.delete(postId);
    }

}