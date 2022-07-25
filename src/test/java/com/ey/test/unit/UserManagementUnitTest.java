package com.ey.test.unit;

import com.ey.test.dto.request.PhoneRequestDTO;
import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.exception.UserManagementException;
import com.ey.test.service.JwtService;
import com.ey.test.service.ValidateService;
import com.ey.test.service.impl.ValidateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserManagementUnitTest {

    @Autowired
    JwtService jwtService;

    @Autowired
    ValidateService validateService;

    @Test
    void validateBadEmails(){
        String email1 ="email@com";
        String email2 = "email@.cl";
        String email3 = "test.email.com";
        String email4 = "email3@mail.cl";
        String email5 = "email.123@test.com";

        Assertions.assertThrows(UserManagementException.class, () -> validateService.validateEmail(email1));
        Assertions.assertThrows(UserManagementException.class, () -> validateService.validateEmail(email2));
        Assertions.assertThrows(UserManagementException.class, () -> validateService.validateEmail(email3));
        Assertions.assertThrows(UserManagementException.class, () -> validateService.validateEmail(email4));
        Assertions.assertThrows(UserManagementException.class, () -> validateService.validateEmail(email5));
    }

    @Test
    void validateGoodEMails() {
        String email1 = "emailtest.com";
        String email2 = "emailtest@test.cl";
        String email3 = "testemail@mail.com";

        ValidateService validateServiceMock = mock(ValidateServiceImpl.class);
        validateServiceMock.validateEmail(email1);
        validateServiceMock.validateEmail(email2);
        validateServiceMock.validateEmail(email3);

        verify(validateServiceMock, times(1)).validateEmail(email1);
        verify(validateServiceMock, times(1)).validateEmail(email2);
        verify(validateServiceMock, times(1)).validateEmail(email3);
    }

    @Test
    void validateData(){
        ValidateService validateServiceMock = mock(ValidateServiceImpl.class);
        validateServiceMock.validateData(getDummyRequestData());
        verify(validateServiceMock, times(1)).validateData(getDummyRequestData());
    }

    @Test
    void validateWrongPhones(){
        Assertions.assertThrows(UserManagementException.class,
                () -> validateService.validateData(getDummyWrongRequestData()));
    }

    @Test
    void generateJwt(){
        String email = "email@test.cl";
        String jwt = jwtService.generateJwt(email);

        Assertions.assertNotNull(jwt);
    }

    private UserRequestDTO getDummyRequestData(){
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Name test");
        requestDTO.setEmail("email@test.cl");
        requestDTO.setPassword("passwordTest12");
        requestDTO.setPhones(getDummyPhoneList());
        return requestDTO;
    }

    private List<PhoneRequestDTO> getDummyPhoneList(){
        PhoneRequestDTO phoneRequestDTO1 = new PhoneRequestDTO();
        phoneRequestDTO1.setNumber("12345678");
        phoneRequestDTO1.setCitycode("56");
        phoneRequestDTO1.setCountrycode("1");

        PhoneRequestDTO phoneRequestDTO2 = new PhoneRequestDTO();
        phoneRequestDTO2.setNumber("23432567");
        phoneRequestDTO2.setCitycode("56");
        phoneRequestDTO2.setCountrycode("2");

        List<PhoneRequestDTO> phones = new ArrayList<>();
        phones.add(phoneRequestDTO1);
        phones.add(phoneRequestDTO2);
        return phones;
    }

    private UserRequestDTO getDummyWrongRequestData(){
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("test");
        requestDTO.setEmail("email@test.cl");
        requestDTO.setPassword("passwordTest12");
        requestDTO.setPhones(getDummyWrongPhoneList());
        return requestDTO;
    }

    private List<PhoneRequestDTO> getDummyWrongPhoneList(){
        PhoneRequestDTO phoneRequestDTO1 = new PhoneRequestDTO();
        phoneRequestDTO1.setNumber("AVD45678");
        phoneRequestDTO1.setCitycode("56");
        phoneRequestDTO1.setCountrycode("1");

        List<PhoneRequestDTO> phones = new ArrayList<>();
        phones.add(phoneRequestDTO1);
        return phones;
    }
}
