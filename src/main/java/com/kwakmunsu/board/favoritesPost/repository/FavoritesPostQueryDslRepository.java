package com.kwakmunsu.board.favoritespost.repository;

import static com.kwakmunsu.board.favoritespost.entity.QFavoritesPost.favoritesPost;
import static com.kwakmunsu.board.likes.entity.QLikes.likes;
import static com.kwakmunsu.board.post.entity.QPost.post;
import static com.querydsl.core.types.Projections.constructor;

import com.kwakmunsu.board.post.entity.dto.PostResponse;
import com.kwakmunsu.board.post.entity.PostSortOption;
import com.kwakmunsu.board.post.service.dto.request.CursorServiceRequest;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FavoritesPostQueryDslRepository {

    private final JPAQueryFactory query;

    public List<PostResponse> findAll(
            CursorServiceRequest cursor,
            PostSortOption option,
            Long memberId
    ) {
        NumberExpression<Long> likeCount = likes.memberId.countDistinct();
        NumberExpression<Long> favoritesCount = favoritesPost.memberId.countDistinct();

        return query.select(
                        constructor(PostResponse.class,
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
                .where(favoritesPost.memberId.eq(memberId),
                        option.createCursorCondition(cursor))
                .groupBy(post.id, post.title, post.writer.nickname, post.createdAt)
                .orderBy(getOrderSpecifier(option))
                .limit(10)
                .fetch();
    }
    // TODO: 코드 중복 됨.
    private OrderSpecifier<?> getOrderSpecifier(PostSortOption option) {
        return switch (option) {
            case ID_DESC -> post.id.desc();
            case ID_ASC -> post.id.asc();
            case TITLE_DESC -> post.title.desc();
            case TITLE_ASC -> post.title.asc();
            case VIEW_COUNT_DESC -> post.viewCount.desc();
            case VIEW_COUNT_ASC -> post.viewCount.asc();
            case CREATE_AT_ASC -> post.createdAt.asc();
            default -> post.createdAt.desc();
        };
    }

}