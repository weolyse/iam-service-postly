package com.postly.iam_service.model.request.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class LoginRequest implements Serializable {

    @Email
    @NonNull
    private String email;

    @NotBlank
    private String password;

}
