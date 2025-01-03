package com.system.athon_stock.domain.excessoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CredentialsException.class)
    public ErrorDTO loginPersonExceptionHandler(CredentialsException ex){
        return new ErrorDTO(
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RegisterPersonException.class)
    public ErrorDTO RuntimeExceptioHandler(RegisterPersonException ex){
        return new ErrorDTO(
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FindByIdException.class)
    public ErrorDTO RuntimeExceptioHandler(FindByIdException ex){
        return new ErrorDTO(
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredTokenException.class)
    public ErrorDTO ExpiredTokenException(ExpiredTokenException ex){
        return new ErrorDTO(
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ReturnNullException.class)
    public ErrorDTO ExpiredTokenException(ReturnNullException ex){
        return new ErrorDTO(
                ex.getMessage()
        );
    }

}
