package com.postly.iam_service.controller;

import com.postly.iam_service.model.dto.UserProfileDto;
import com.postly.iam_service.model.entity.RefreshToken;
import com.postly.iam_service.model.request.authentication.LoginRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.service.AuthService;
import com.postly.iam_service.service.RefreshTokenService;
import com.postly.iam_service.utils.ApiUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginRequest request,
            HttpServletResponse response
    ) {

        IamResponse<UserProfileDto> iamResponse = authService.login(request);
        Cookie authCookie = ApiUtils.createAuthCookie(iamResponse.getPayload().getToken());
        response.addCookie(authCookie);

        return ResponseEntity.ok(iamResponse);
    }

    @GetMapping("/refresh/token")
    public ResponseEntity<IamResponse<UserProfileDto>> refreshToken(
            @RequestParam String refreshToken,
            HttpServletResponse response
    ) {
        IamResponse<UserProfileDto> iamResponse = authService.refreshAccessToken(refreshToken);

        Cookie authCookie = ApiUtils.createAuthCookie(iamResponse.getPayload().getToken());
        response.addCookie(authCookie);

        return ResponseEntity.ok(iamResponse);
    }

}
