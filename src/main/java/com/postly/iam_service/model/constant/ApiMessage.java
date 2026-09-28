package com.postly.iam_service.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiMessage {
    TOKEN_CREATED_OR_UPDATED("Token was created or updated!");

    private String message;

}
