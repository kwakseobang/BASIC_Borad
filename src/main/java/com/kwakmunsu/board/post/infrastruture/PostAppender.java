package com.kwakmunsu.board.post.infrastruture;


import com.kwakmunsu.board.post.entity.Post;
import com.kwakmunsu.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostAppender {

    private final PostRepository postRepository;

    public Post append(String title, String content, Long memberId) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .writerId(memberId)
                .build();
        return postRepository.append(post);
    }

}