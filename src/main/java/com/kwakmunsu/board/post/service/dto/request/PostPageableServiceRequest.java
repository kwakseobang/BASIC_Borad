package com.kwakmunsu.board.post.service.dto.request;

public record PostPageableServiceRequest(
        int page,
        int pageSize,
        String sortBy,
        boolean isDesc
) {

}