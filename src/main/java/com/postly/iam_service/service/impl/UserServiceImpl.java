package com.postly.iam_service.service.impl;

import com.postly.iam_service.mapper.UserMapper;
import com.postly.iam_service.model.constant.ApiErrorMessage;
import com.postly.iam_service.model.dto.UserDto;
import com.postly.iam_service.model.entity.Role;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.model.entity.UserSystemRole;
import com.postly.iam_service.model.exception.InvalidDataException;
import com.postly.iam_service.model.exception.NotFoundException;
import com.postly.iam_service.model.request.user.CreateUserRequest;
import com.postly.iam_service.model.request.user.UpdateUserRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.model.response.PaginationResponse;
import com.postly.iam_service.repository.RoleRepository;
import com.postly.iam_service.repository.UserRepository;
import com.postly.iam_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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

        Role defaultRole = roleRepository.findByName(UserSystemRole.USER.name())
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.ROLE_NOT_FOUND.getMessage(UserSystemRole.USER.name())));

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(defaultRole);

        User userToSave = userMapper.toUser(request);
        userToSave.setRoles(roles);
        userToSave.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(userToSave);
        UserDto userDto = userMapper.toUserDto(savedUser);

        return IamResponse.createSuccessful(userDto);
    }

    @Override
    public IamResponse<UserDto> update(Integer userId, UpdateUserRequest request) {
        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(userId)));

        if (userRepository.existsByUsername(request.getUsername()) && !request.getUsername().equals(user.getUsername())) {
            throw new InvalidDataException(ApiErrorMessage.DUPLICATE_USERNAME.getMessage(request.getUsername()));
        }

        if (userRepository.existsByEmail(request.getEmail()) && !user.getEmail().equals(request.getEmail())) {
            throw new InvalidDataException(ApiErrorMessage.DUPLICATE_EMAIL.getMessage(request.getEmail()));
        }

        userMapper.update(user, request);
        user.setUpdated(LocalDateTime.now());
        userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(user);

        return IamResponse.createSuccessful(userDto);
    }

    @Override
    public void softDelete(Integer userId) {

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(userId)));

        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public IamResponse<PaginationResponse<UserDto>> findAll(Pageable pageable) {
        Page<UserDto> page = userRepository.findAll(pageable)
                .map(userMapper::toUserDto);

        PaginationResponse<UserDto> paginationResponse = new PaginationResponse<>(
                page.getContent(),
                PaginationResponse.Pagination.builder()
                        .total(page.getTotalElements())
                        .limit(page.getSize())
                        .page(page.getNumber())
                        .pages(page.getTotalPages())
                        .build()
        );

        return IamResponse.createSuccessful(paginationResponse);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserDetails(email, userRepository);
    }

    static UserDetails getUserDetails(String email, UserRepository userRepository) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(email)));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}
