package com.ey.test.controller;

import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.dto.response.UserResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserManagementController {

    ResponseEntity<UserResponseDTO> signup(UserRequestDTO UserRequestDTO);
}
