package com.kwakmunsu.board.post.infrastruture;


import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostAppender {

    private final PostRepository postRepository;

    public void append(String title, String content, Long memberId) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .writerId(memberId)
                .build();

        postRepository.append(post);
    }

}