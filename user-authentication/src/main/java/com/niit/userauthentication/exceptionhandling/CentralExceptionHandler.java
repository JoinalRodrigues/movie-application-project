package com.niit.userauthentication.exceptionhandling;

import com.niit.userauthentication.dto.ErrorMessageDTO;
import com.niit.userauthentication.exception.*;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= InvalidCredentialsException.class)
    public ResponseEntity<ErrorMessageDTO> handleUnauthorizedException(InvalidCredentialsException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value= ImageTooLargeException.class)
    public ResponseEntity<ErrorMessageDTO> handleImageTooLargeException(ImageTooLargeException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(value= IOException.class)
    public ResponseEntity<ErrorMessageDTO> handleIOException(IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value= TokenExpiredException.class)
    public ResponseEntity<ErrorMessageDTO> handleTokenExpiredException(TokenExpiredException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value= MalformedJwtException.class)
    public ResponseEntity<ErrorMessageDTO> handleMalformedJwtException(MalformedJwtException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= UserNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleUserNotFoundException(UserNotFoundException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= RoleNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleRoleNotFoundException(RoleNotFoundException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= NullPointerException.class)
    public ResponseEntity<ErrorMessageDTO> handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= UserEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorMessageDTO> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value= UserNotEnabledException.class)
    public ResponseEntity<ErrorMessageDTO> handleUserNotEnabledException(UserNotEnabledException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
