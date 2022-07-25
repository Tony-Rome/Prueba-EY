package com.ey.test.integration;

import com.ey.test.controller.UserManagementController;
import com.ey.test.dao.entity.UserEntity;
import com.ey.test.dao.repository.PhoneRepository;
import com.ey.test.dao.repository.UserRepository;
import com.ey.test.dto.request.PhoneRequestDTO;
import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.dto.response.UserResponseDTO;
import com.ey.test.util.DatetimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserManagementIntegrationTest {

    @Autowired
    private UserManagementController userManagementController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PhoneRepository phoneRepository;

    @Test
    void signupValidUser(){
        when(userRepository.save(any())).thenReturn(getDummyUserEntity());
        when(phoneRepository.save(any())).thenReturn(any());

        ResponseEntity<UserResponseDTO> response = userManagementController.signup(getDummyRequestData());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(response.getBody());

    }

    private UserRequestDTO getDummyRequestData(){
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("First name");
        requestDTO.setEmail("emailtest@ey.cl");
        requestDTO.setPassword("passwordEy12");
        requestDTO.setPhones(getDummyPhoneList());
        return requestDTO;
    }

    private List<PhoneRequestDTO> getDummyPhoneList(){
        PhoneRequestDTO phoneRequestDTO1 = new PhoneRequestDTO();
        phoneRequestDTO1.setNumber("12345678");
        phoneRequestDTO1.setCitycode("12");
        phoneRequestDTO1.setCountrycode("56");

        List<PhoneRequestDTO> phones = new ArrayList<>();
        phones.add(phoneRequestDTO1);
        return phones;
    }

    private UserEntity getDummyUserEntity(){
        return UserEntity.builder()
                .id("idTest")
                .token("tokenTest")
                .email("email@test.cl")
                .lastLogin(DatetimeUtil.getCurrentDateTime())
                .created(DatetimeUtil.getCurrentDateTime())
                .modified(DatetimeUtil.getCurrentDateTime())
                .isActive(true).build();
    }
}
