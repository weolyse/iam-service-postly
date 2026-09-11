package com.postly.iam_service.model.request;

import com.postly.iam_service.model.enums.PostSortField;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SearchPostRequest implements Serializable {

    private String title;
    private String content;
    private Integer likes;

    private Boolean deleted;
    private String keyword;
    private PostSortField postSortField;

}
