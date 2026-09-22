package com.postly.iam_service.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ApiErrorMessage {

    POST_NOT_FOUND_BY_ID("Couldn't find post with ID: %s"),
    DUPLICATE_TITLE("The post with title: %s already exists!"),
    USER_NOT_FOUND_BY_ID("Couldn't find user with ID: %s"),
    USER_NOT_FOUND_BY_EMAIL("Couldn't find user with email: %s"),
    DUPLICATE_USERNAME("The user with username: %s already exists!"),
    DUPLICATE_EMAIL("The user with email: %s already exists!"),
    ROLE_NOT_FOUND("Role with name: %s not found!"),

    INVALID_TOKEN_SIGNATURE("Invalid token signature"),
    ERROR_DURING_JWT_PROCESSING("An unexpected error occurred during JWT processing"),
    TOKEN_EXPIRED("Token expired."),
    UNEXPECTED_ERROR_OCCURRED("An unexpected error occurred. Please try again later.");


    private final String message;

    public String getMessage(Object o) {
        return String.format(message, o);
    }
}
