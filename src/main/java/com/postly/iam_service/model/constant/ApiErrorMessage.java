package com.postly.iam_service.model.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiErrorMessage {
    POST_NOT_FOUND_BY_ID("Couldn't find post with ID: %d");

    private final String message;

    public String getMessage(Integer id) {
        return String.format(message, id);
    }
}
