package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.comment.infrastruture.CommentReader;
import com.kwakmunsu.board.likes.infrastruture.LikesReader;
import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostAppender;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import com.kwakmunsu.board.post.infrastruture.PostUpdater;
import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import com.kwakmunsu.board.post.service.dto.response.PostViewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostAppender postAppender;
    private final PostReader postReader;
    private final LikesReader likesReader;
    private final CommentReader commentReader;
    //

    public void create(PostCreateCommand postCreateCommand) {
        postAppender.append(
                postCreateCommand.title(),
                postCreateCommand.content(),
                postCreateCommand.memberId()
        );
    }


    @Transactional
    public void update(PostUpdateCommand postUpdateCommand) {
        Post post = postReader.read(postUpdateCommand.postId());
        post.updatePost(postUpdateCommand.title(), postUpdateCommand.content());
    }

    @Transactional
    public void updateViews(Long postId) {
        Post post = postReader.read(postId);
        post.incrementViewCount();
    }

    public PostViewsResponse readViews(Long postId) {
        Post post = postReader.read(postId);

        return new PostViewsResponse(post.getViewCount());
    }

}