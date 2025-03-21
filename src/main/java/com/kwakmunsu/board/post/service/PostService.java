package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostAppender;
import com.kwakmunsu.board.post.infrastruture.PostReader;
import com.kwakmunsu.board.post.infrastruture.PostUpdater;
import com.kwakmunsu.board.post.service.dto.request.PostCreateCommand;
import com.kwakmunsu.board.post.service.dto.request.PostUpdateCommand;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostAppender postAppender;
    private final PostUpdater postUpdater;
    private final PostReader postReader;

    public PostResponse create(PostCreateCommand postCreateCommand) {
        Post post = postAppender.append(
                postCreateCommand.title(),
                postCreateCommand.content(),
                postCreateCommand.memberId()
        );

        return PostResponse.from(post);
    }

    @Transactional
    public void update(PostUpdateCommand postUpdateCommand) {
        Post post = postReader.read(postUpdateCommand.postId());
        post.updatePost(postUpdateCommand.title(), postUpdateCommand.content());
    }

}