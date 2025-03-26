package com.kwakmunsu.board.post.entity;

import static com.kwakmunsu.board.post.entity.QPost.post;

import com.kwakmunsu.board.global.exception.BadRequestException;
import com.kwakmunsu.board.global.response.error.ErrorCode;
import com.kwakmunsu.board.post.repository.CursorServiceRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.stream.Stream;

public enum PostSortOption {

    ID_DESC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastId() == null) {
                return null;
            }
            return post.id.lt(cursor.lastId());
        }
    },
    ID_ASC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastId() == null) {
                return null;
            }
            return post.id.gt(cursor.lastId());
        }
    },
    VIEW_COUNT_DESC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastViews() == null) {
                return null;
            }
            return post.viewCount.lt(cursor.lastViews())
                    .or(post.viewCount.eq(cursor.lastViews())
                            .and(post.id.lt(cursor.lastId())));
        }
    },
    VIEW_COUNT_ASC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastViews() == null) {
                return null;
            }
            return post.viewCount.gt(cursor.lastViews())
                    .or(post.viewCount.eq(cursor.lastViews())
                            .and(post.id.gt(cursor.lastId())));
        }
    },
    TITLE_DESC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastTitle() == null) {
                return null;
            }
            return post.title.lt(cursor.lastTitle())
                    .or(post.title.eq(cursor.lastTitle())
                            .and(post.id.lt(cursor.lastId())));
        }
    },
    TITLE_ASC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastTitle() == null) {
                return null;
            }
            return post.title.gt(cursor.lastTitle())
                    .or(post.title.eq(cursor.lastTitle())
                            .and(post.id.gt(cursor.lastId())));
        }
    },
    CREATE_AT_DESC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastCreatedAt() == null) {
                return null;
            }
            return post.createdAt.lt(cursor.lastCreatedAt())
                    .or(post.createdAt.eq(cursor.lastCreatedAt())
                            .and(post.id.lt(cursor.lastId())));
        }
    },
    CREATE_AT_ASC {
        @Override
        public BooleanExpression createCursorCondition(CursorServiceRequest cursor) {
            if (cursor.lastCreatedAt() == null) {
                return null;
            }
            return post.createdAt.gt(cursor.lastCreatedAt())
                    .or(post.createdAt.eq(cursor.lastCreatedAt())
                            .and(post.id.gt(cursor.lastId())));
        }
    };

    public abstract BooleanExpression createCursorCondition(CursorServiceRequest cursor);

    public static PostSortOption toSortOption(String option) {
        return Stream.of(PostSortOption.values())
                .filter(type -> type.toString().equals(option.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(
                        ErrorCode.BAD_REQUEST_ARGUMENT,
                        "정확한 정렬 조건을 입력해주세요")
                );
    }

}