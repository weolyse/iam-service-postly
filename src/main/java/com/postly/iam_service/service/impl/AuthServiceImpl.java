package com.postly.iam_service.service.impl;

import com.postly.iam_service.mapper.UserMapper;
import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.dto.UserProfileDto;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.exception.InvalidDataException;
import com.postly.iam_service.model.request.authentication.LoginRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.repository.UserRepository;
import com.postly.iam_service.security.JwtTokenProvider;
import com.postly.iam_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public IamResponse<UserProfileDto> login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new InvalidDataException(ApiErrorMessage.INVALID_EMAIL_OR_PASSWORD.getMessage());
        }

        User user = userRepository.findByEmailAndDeletedFalse(request.getEmail())
                .orElseThrow(() -> new InvalidDataException(ApiErrorMessage.INVALID_EMAIL_OR_PASSWORD.getMessage()));

        String token = jwtTokenProvider.generateToken(user);

        UserProfileDto response = userMapper.toUserProfileDto(user, token);

        return IamResponse.createSuccessfulWithToken(response);
    }
}
