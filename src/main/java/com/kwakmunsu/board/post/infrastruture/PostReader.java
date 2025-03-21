package com.kwakmunsu.board.post.infrastruture;


import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostReader {

    private final PostRepository postRepository;

    public Post read(Long postId) {
        return postRepository.read(postId);
    }

}