package com.system.athon_stock.domain.excessoes;

public class FindByIdException extends RuntimeException {
    public FindByIdException(String message) {
        super(message);
    }
}
