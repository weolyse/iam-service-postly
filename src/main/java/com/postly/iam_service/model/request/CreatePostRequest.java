package com.postly.iam_service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CreatePostRequest implements Serializable {

    @NotBlank(message = "Title cannot be blank!")
    private final String title;
    @NotBlank(message = "Content cannot be blank")
    private final String content;

}
