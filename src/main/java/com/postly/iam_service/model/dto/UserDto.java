package com.postly.iam_service.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.postly.iam_service.model.entity.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "username",
        "email",
        "registrationStatus",
        "lastLogin",
        "created",
        "updated",
        "deleted"
})
public class UserDto implements Serializable {

    private Integer id;
    private String username;
    private String email;
    private String registrationStatus;
    private LocalDateTime lastLogin;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean deleted;

}
