package com.system.athon_stock.domain.excessoes;

public class RegisterPersonException extends RuntimeException {

    public RegisterPersonException(String message) {
        super(message);
    }
}
