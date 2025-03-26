package com.kwakmunsu.board.post.repository;

import static com.kwakmunsu.board.favoritespost.entity.QFavoritesPost.favoritesPost;
import static com.kwakmunsu.board.likes.entity.QLikes.likes;
import static com.kwakmunsu.board.post.entity.QPost.post;

import com.kwakmunsu.board.post.entity.PostPaginationResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostPaginationRepository {

    private final JPAQueryFactory query;
    private static final int SIZE = 10;

    public List<PostPaginationResponse> findAll(
            CursorServiceRequest cursor,
            PostSortOption option
    ) {
        NumberExpression<Long> likeCount = likes.memberId.countDistinct();
        NumberExpression<Long> favoritesCount = favoritesPost.memberId.countDistinct();
        return query.select(
                        Projections.constructor(PostPaginationResponse.class,
                                post.id.as("postId"),
                                post.title,
                                post.writer.nickname,
                                post.createdAt,
                                post.viewCount,
                                likeCount,
                                favoritesCount
                        )
                )
                .from(post)
                .leftJoin(likes).on(post.id.eq(likes.postId))
                .leftJoin(favoritesPost).on(post.id.eq(favoritesPost.postId))
                .where(option.createCursorCondition(cursor))
                .groupBy(post.id, post.title, post.writer.nickname, post.createdAt)
                .orderBy(getOrderSpecifier(option))
                .limit(SIZE)
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifier(PostSortOption option) {
        return switch (option) {
            case ID_DESC -> post.id.desc();
            case ID_ASC -> post.id.asc();
            case TITLE_DESC -> post.title.desc();
            case TITLE_ASC -> post.title.asc();
            case VIEW_COUNT_DESC -> post.viewCount.desc();
            case VIEW_COUNT_ASC -> post.viewCount.asc();
            case CREATE_AT_ASC -> post.createdAt.asc();
            default ->  post.createdAt.desc();
        };
    }

}