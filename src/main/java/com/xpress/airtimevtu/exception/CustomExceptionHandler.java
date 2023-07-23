package com.xpress.airtimevtu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomerRegistrationException.class})
    public ResponseEntity<?> handleCustomerRegistrationException(CustomerRegistrationException customerRegistrationException){
        return ResponseEntity.badRequest().body(customerRegistrationException.getMessage());
    }

    @ExceptionHandler(AirtimeException.class)
    public ResponseEntity<?> handleAirtimeException(AirtimeException airtimeException){
        return ResponseEntity.badRequest().body(airtimeException.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleBadCredentialException(AuthenticationException authenticationException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage() + "username or password is not valid");
    }

}
