package com.flow.fast_food_flow.domain.excessoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LoginPersonExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LoginPersonException.class)
    public ErrorDTO handler(LoginPersonException ex){
        return new ErrorDTO(
                ex.getMessage()
        );
    }
}
