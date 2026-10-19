package com.postly.iam_service.service;

import com.postly.iam_service.model.dto.response.UserDto;
import com.postly.iam_service.model.dto.response.UserProfileDto;
import com.postly.iam_service.model.dto.request.authentication.RegistrationRequest;
import com.postly.iam_service.model.dto.request.user.UpdateUserRequest;
import com.postly.iam_service.model.dto.request.user.CreateUserRequest;
import com.postly.iam_service.model.format.IamResponse;
import com.postly.iam_service.model.format.PaginationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    IamResponse<UserDto> findById(Integer id);

    IamResponse<UserDto> create(CreateUserRequest request);

    IamResponse<UserDto> update(Integer userId, UpdateUserRequest request);

    void softDelete(Integer userId);

    IamResponse<PaginationResponse<UserDto>> findAll(Pageable pageable);

}
