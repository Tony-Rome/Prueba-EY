package com.ey.test.controller.impl;

import com.ey.test.controller.UserManagementController;
import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.dto.response.UserResponseDTO;
import com.ey.test.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/user")
@RequiredArgsConstructor
public class UserManagementControllerImp implements UserManagementController {

    private final UserManagementService userManagementService;

    @Override
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userManagementService.signup(userRequestDTO), HttpStatus.CREATED);
    }
}
