package com.postly.iam_service.service.impl;

import com.postly.iam_service.mapper.UserMapper;
import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.dto.UserDto;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.exception.InvalidDataException;
import com.postly.iam_service.model.exception.NotFoundException;
import com.postly.iam_service.model.request.user.CreateUserRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.repository.UserRepository;
import com.postly.iam_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public IamResponse<UserDto> findById(Integer id) {
        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(id)));

        UserDto userDto = userMapper.toUserDto(user);
        return IamResponse.createSuccessful(userDto);
    }

    @Override
    public IamResponse<UserDto> create(CreateUserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new InvalidDataException(ApiErrorMessage.DUPLICATE_USERNAME.getMessage(request.getUsername()));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidDataException(ApiErrorMessage.DUPLICATE_EMAIL.getMessage(request.getEmail()));
        }

        User userToSave = userMapper.toUser(request);
        User savedUser = userRepository.save(userToSave);
        UserDto userDto = userMapper.toUserDto(savedUser);

        return IamResponse.createSuccessful(userDto);
    }
}
