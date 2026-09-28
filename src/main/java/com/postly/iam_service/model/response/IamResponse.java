package com.postly.iam_service.model.response;

import com.postly.iam_service.model.constant.ApiMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class IamResponse<P extends Serializable> implements Serializable {

    private String message;
    private P payload;
    private boolean success;

    public static <P extends Serializable> IamResponse<P> createSuccessful(P payload) {
        return new IamResponse<>(StringUtils.EMPTY, payload, true);
    }

    public static <P extends Serializable> IamResponse<P> createSuccessfulWithToken(P payload) {
        return new IamResponse<>(ApiMessage.TOKEN_CREATED_OR_UPDATED.getMessage(), payload, true);
    }
}
