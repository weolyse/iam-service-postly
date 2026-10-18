package com.postly.iam_service.model.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
        "deleted",
        "roles"
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
    private List<RoleDto> roles;

}
