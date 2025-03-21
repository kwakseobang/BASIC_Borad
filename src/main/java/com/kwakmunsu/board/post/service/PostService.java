package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostAppender;
import com.kwakmunsu.board.post.service.dto.PostCreateCommand;
import com.kwakmunsu.board.post.service.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostAppender postAppender;

    public PostResponse create(PostCreateCommand postCreateCommand) {
        Post post = postAppender.append(
                postCreateCommand.title(),
                postCreateCommand.content(),
                postCreateCommand.memberId()
        );
        return new PostResponse(post);
    }

}