package com.kwakmunsu.board.post.infrastruture;


import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostUpdater {

    private final PostRepository postRepository;

}