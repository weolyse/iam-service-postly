package com.postly.iam_service.model.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UpdateUserRequest {

    @NotBlank(message = "Username cannot be blank!")
    private final String username;
    @NotBlank(message = "Password cannot be blank!")
    private final String password;
    @NotBlank(message = "Email cannot be blank!")
    private final String email;

}
