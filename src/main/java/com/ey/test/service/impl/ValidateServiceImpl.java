package com.ey.test.service.impl;

import com.ey.test.dto.request.PhoneRequestDTO;
import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.exception.UserManagementException;
import com.ey.test.service.ValidateService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ey.test.util.ErrorMessageUtil.*;

@Service
public class ValidateServiceImpl implements ValidateService {

    private final static String EMAIL_REGEX = "[a-zA-Z]{2,}@[a-zA-Z]{2,}\\.[a-zA-Z]{2,5}";
    private final static String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d.*\\d)(?!(.*[A-Z]){2,}|(.*[0-9]){3,})(?!.*[^\\w\\d]).{5,}$";


    @Override
    public void validateData(UserRequestDTO userRequestDTO) {
        validateName(userRequestDTO.getName());
        validateEmail(userRequestDTO.getEmail());
        validatePassword(userRequestDTO.getPassword());
        validatePhones(userRequestDTO.getPhones());
    }

    private void validateName(String name){
        if(name == null || !name.matches("[a-zA-Z\\s]{3,}"))
            throw new UserManagementException(NAME_ERR_DESC,HttpStatus.BAD_REQUEST);
    }

    @Override
    public void validateEmail(String email){
     if(email == null || !email.matches(EMAIL_REGEX))
         throw new UserManagementException(EMAIL_ERR_DESC, HttpStatus.BAD_REQUEST);
    }

    @Override
    public void validatePassword(String password){
        if(password == null || !password.matches(PASSWORD_REGEX))
            throw new UserManagementException(PASSWORD_ERR_DESC, HttpStatus.BAD_REQUEST);
    }

    private void validatePhones(List<PhoneRequestDTO> phonesDTO){
        if(phonesDTO == null || phonesDTO.isEmpty())
            throw new UserManagementException(EMPTY_PHONE_ERR_DESC, HttpStatus.BAD_REQUEST);

        phonesDTO
                .forEach(phone -> {
                    validatePhoneNumber(phone.getNumber());
                    validateCityCode(phone.getCitycode());
                    validateCountryCode(phone.getCountrycode());
                });
    }

    private void validatePhoneNumber(String phoneNumber){
        if(!phoneNumber.matches("\\d{8}"))
            throw new UserManagementException(PHONE_ERR_DESC, HttpStatus.BAD_REQUEST);
    }

    private void validateCityCode(String cityCode){
        if(!cityCode.matches("\\d+"))
            throw new UserManagementException(CITY_CODE_ERR_DESC, HttpStatus.BAD_REQUEST);
    }

    private void validateCountryCode(String countryCode){
        if(!countryCode.matches("\\d{2}"))
            throw new UserManagementException(COUNTRY_CODE_ERR_DESC, HttpStatus.BAD_REQUEST);
    }
}
