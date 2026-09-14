package com.postly.iam_service.controller;

import com.postly.iam_service.model.dto.UserDto;
import com.postly.iam_service.model.request.user.CreateUserRequest;
import com.postly.iam_service.model.response.IamResponse;
import com.postly.iam_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<IamResponse<UserDto>> getUserById(
            @PathVariable Integer id
    ) {
        IamResponse<UserDto> response = userService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<IamResponse<UserDto>> createUser(
            @RequestBody @Valid CreateUserRequest request
    ) {
        IamResponse<UserDto> response = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
