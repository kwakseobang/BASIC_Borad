package com.kwakmunsu.board.post.service.dto.request;

public record PostPageableCommand(
        int page,
        int pageSize,
        String sortBy,
        boolean isDesc
) {

}