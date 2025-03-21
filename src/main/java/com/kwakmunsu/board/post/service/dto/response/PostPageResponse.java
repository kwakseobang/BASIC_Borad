package com.kwakmunsu.board.post.service.dto.response;

import com.kwakmunsu.board.post.entity.Post;
import org.springframework.data.domain.Page;

public record PostPageResponse(Page<Post> posts) { }