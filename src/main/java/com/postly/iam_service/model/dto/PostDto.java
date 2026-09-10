package com.postly.iam_service.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonPropertyOrder({
        "id",
        "title",
        "content",
        "likes",
        "created",
        "updated",
        "deleted"
})
public class PostDto implements Serializable {

    private Integer id;
    private String title;
    private String content;
    private Integer likes;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean deleted;
}
