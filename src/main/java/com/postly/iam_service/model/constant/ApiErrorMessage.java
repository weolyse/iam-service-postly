package com.postly.iam_service.model.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiErrorMessage {
    POST_NOT_FOUND_BY_ID("Couldn't find post with ID: %s"),
    DUPLICATE_TITLE("The post with title: %s already exists!");

    private final String message;

    public String getMessage(Object o) {
        return String.format(message, o);
    }
}
