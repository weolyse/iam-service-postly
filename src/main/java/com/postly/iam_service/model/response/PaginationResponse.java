package com.postly.iam_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class PaginationResponse<T> implements Serializable {

    private List<T> content;
    private Pagination pagination;

    @Getter
    @AllArgsConstructor
    public static class Pagination implements Serializable {
        private long total;
        private int limit;
        private int page;
        private int pages;

    }
}
