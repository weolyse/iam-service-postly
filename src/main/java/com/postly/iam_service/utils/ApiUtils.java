package com.postly.iam_service.utils;

import jakarta.servlet.http.Cookie;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;

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

}
