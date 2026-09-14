package com.postly.iam_service.model.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiErrorMessage {

    POST_NOT_FOUND_BY_ID("Couldn't find post with ID: %s"),
    DUPLICATE_TITLE("The post with title: %s already exists!"),
    USER_NOT_FOUND_BY_ID("Couldn't find user with ID: %s"),
    DUPLICATE_USERNAME("The user with username: %s already exists!"),
    DUPLICATE_EMAIL("The user with email: %s already exists!");

    private final String message;

    public String getMessage(Object o) {
        return String.format(message, o);
    }
}
