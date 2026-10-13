package com.postly.iam_service.utils;

import com.postly.iam_service.model.constant.ApiConstants;
import jakarta.servlet.http.Cookie;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import java.util.UUID;

@UtilityClass
public class ApiUtils {

    public static Cookie createAuthCookie(String token) {
        Cookie authorizationCookie = new Cookie(HttpHeaders.AUTHORIZATION, token);
        authorizationCookie.setHttpOnly(true);
        authorizationCookie.setSecure(true);
        authorizationCookie.setPath("/");
        authorizationCookie.setMaxAge(300);
        return authorizationCookie;
    }

    public static String generateUuidWithoutDash() {
        return UUID.randomUUID().toString().replace(ApiConstants.DASH, StringUtils.EMPTY);
    }
}
