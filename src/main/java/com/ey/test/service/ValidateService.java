package com.ey.test.service;

import com.ey.test.dto.request.UserRequestDTO;

public interface ValidateService {

    void validateData(UserRequestDTO userRequestDTO);
    void validateEmail(String email);
    void validatePassword(String password);
}
