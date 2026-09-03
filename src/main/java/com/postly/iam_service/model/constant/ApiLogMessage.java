package com.postly.iam_service.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiLogMessage {
    POST_INFO_BY_ID("Receiving post with ID: %d");

    private final String message;

    public String getMessage(Integer id) {
        return String.format(message, id);
    }
}
