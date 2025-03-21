package com.kwakmunsu.board.favoritespost.service.dto;

import com.kwakmunsu.board.post.entity.Post;
import java.util.List;

public record FavoritesResponse(List<Post> posts) { }