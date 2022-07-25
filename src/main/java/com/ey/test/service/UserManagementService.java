package com.ey.test.service;

import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.dto.response.UserResponseDTO;

public interface UserManagementService {

    UserResponseDTO signup(UserRequestDTO userRequestDTO);
}
