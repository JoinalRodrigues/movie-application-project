package com.niit.pushnotification.exceptionhandling;



import com.niit.pushnotification.dto.ErrorMessageDTO;
import com.niit.pushnotification.exception.InvalidCredentialsException;
import com.niit.pushnotification.exception.TokenExpiredException;
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

}
