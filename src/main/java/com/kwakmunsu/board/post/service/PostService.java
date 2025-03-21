package com.kwakmunsu.board.post.service;

import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.infrastruture.PostAppender;
import com.kwakmunsu.board.post.service.dto.request.PostCommand;
import com.kwakmunsu.board.post.service.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostAppender postAppender;

    public PostResponse create(PostCommand postCommand) {
        Post post = postAppender.append(
                postCommand.title(),
                postCommand.content(),
                postCommand.memberId()
        );
        return PostResponse.from(post);
    }

}