package com.ey.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.ey.test.util.ErrorMessageUtil.GLOBAL_ERR_DESC;

@ControllerAdvice
public class UserManagementHandlerAdvice {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handlException(Exception ex){
        return new ResponseEntity<>(new ErrorMessage(GLOBAL_ERR_DESC), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserManagementException.class})
    public ResponseEntity<ErrorMessage> handleUserManagementException(UserManagementException ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), ex.getHttpStatus());
    }
}
