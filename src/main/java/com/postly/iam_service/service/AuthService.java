package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.response.UserProfileDto;
import com.postly.iam_service.model.dto.request.authentication.LoginRequest;
import com.postly.iam_service.model.format.IamResponse;

public interface AuthService {

    IamResponse<UserProfileDto> login(LoginRequest request);

    IamResponse<UserProfileDto> refreshAccessToken(String refreshToken);
}
