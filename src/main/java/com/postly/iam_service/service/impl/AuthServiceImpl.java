package com.postly.iam_service.service.impl;

import com.postly.iam_service.mapper.UserMapper;
import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.dto.request.authentication.RegistrationRequest;
import com.postly.iam_service.model.dto.response.UserProfileDto;
import com.postly.iam_service.model.entity.RefreshToken;
import com.postly.iam_service.model.entity.Role;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.entity.UserSystemRole;
import com.postly.iam_service.model.exception.DuplicateDataException;
import com.postly.iam_service.model.exception.InvalidDataException;
import com.postly.iam_service.model.dto.request.authentication.LoginRequest;
import com.postly.iam_service.model.exception.NotFoundException;
import com.postly.iam_service.model.format.IamResponse;
import com.postly.iam_service.repository.RefreshTokenRepository;
import com.postly.iam_service.repository.RoleRepository;
import com.postly.iam_service.repository.UserRepository;
import com.postly.iam_service.security.JwtTokenProvider;
import com.postly.iam_service.service.AuthService;
import com.postly.iam_service.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
        String refreshToken = refreshTokenService.generateOrUpdateRefreshToken(user).getToken();
        UserProfileDto response = userMapper.toUserProfileDto(user, token, refreshToken);

        return IamResponse.createSuccessfulWithToken(response);
    }

    @Override
    public IamResponse<UserProfileDto> register(RegistrationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateDataException(ApiErrorMessage.DUPLICATE_USERNAME.getMessage(request.getUsername()));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateDataException(ApiErrorMessage.DUPLICATE_EMAIL.getMessage(request.getEmail()));
        }

        User user = userMapper.toUser(request);
        Role userRole = roleRepository.findByName(UserSystemRole.USER.name())
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.ROLE_NOT_FOUND.getMessage(UserSystemRole.USER.name())));

        List<Role> roleList = new ArrayList<>();
        roleList.add(userRole);
        user.setRoles(roleList);

        String encoded = passwordEncoder.encode(request.getPassword());
        user.setPassword(encoded);

        userRepository.save(user);

        String accessToken = jwtTokenProvider.generateToken(user);
        String refreshToken = refreshTokenService.generateOrUpdateRefreshToken(user).getToken();

        UserProfileDto userProfileDto = userMapper.toUserProfileDto(user, accessToken, refreshToken);

        return IamResponse.createSuccessfulWithToken(userProfileDto);
    }


    @Override
    public IamResponse<UserProfileDto> refreshAccessToken(String refreshTokenValue) {
        RefreshToken newRefreshToken = refreshTokenService.validateAndRefreshToken(refreshTokenValue);

        User user = newRefreshToken.getUser();
        String newAccessToken = jwtTokenProvider.generateToken(user);

        return IamResponse.createSuccessfulWithToken(
                userMapper.toUserProfileDto(user, newAccessToken, newRefreshToken.getToken())
        );
    }
}
