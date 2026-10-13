package com.postly.iam_service.service.impl;

import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.entity.RefreshToken;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.exception.NotFoundException;
import com.postly.iam_service.repository.RefreshTokenRepository;
import com.postly.iam_service.service.RefreshTokenService;
import com.postly.iam_service.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateOrUpdateRefreshToken(User user) {
        return refreshTokenRepository.findByUserId(user.getId())
                .map(refreshToken -> {
                    refreshToken.setToken(ApiUtils.generateUuidWithoutDash());
                    refreshToken.setCreatedAt(LocalDateTime.now());
                    return refreshTokenRepository.save(refreshToken);
                })
                .orElseGet(() -> {
                    RefreshToken refreshToken = new RefreshToken();
                    refreshToken.setToken(ApiUtils.generateUuidWithoutDash());
                    refreshToken.setUser(user);
                    return refreshTokenRepository.save(refreshToken);
                });
    }

    @Override
    public RefreshToken validateAndRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.REFRESH_TOKEN_NOT_FOUND.getMessage()));

        refreshToken.setCreatedAt(LocalDateTime.now());
        refreshToken.setToken(ApiUtils.generateUuidWithoutDash());
        return refreshTokenRepository.save(refreshToken);
    }
}
