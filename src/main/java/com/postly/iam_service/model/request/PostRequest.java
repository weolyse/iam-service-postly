package com.postly.iam_service.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PostRequest implements Serializable {

    private final String title;
    private final String content;

}
