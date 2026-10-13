package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.UserProfileDto;
import com.postly.iam_service.model.request.authentication.LoginRequest;
import com.postly.iam_service.model.response.IamResponse;

public interface AuthService {

    IamResponse<UserProfileDto> login(LoginRequest request);

    IamResponse<UserProfileDto> refreshAccessToken(String refreshToken);
}
