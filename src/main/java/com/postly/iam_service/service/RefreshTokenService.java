package com.postly.iam_service.service;

import com.postly.iam_service.model.entity.RefreshToken;
import com.postly.iam_service.model.entity.User;

public interface RefreshTokenService {

    RefreshToken generateOrUpdateRefreshToken(User user);

    RefreshToken validateAndRefreshToken(String token);

}
