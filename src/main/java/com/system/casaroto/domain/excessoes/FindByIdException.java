package com.system.casaroto.domain.excessoes;

public class FindByIdException extends RuntimeException {
    public FindByIdException(String message) {
        super(message);
    }
}
