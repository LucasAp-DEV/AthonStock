package com.system.athon_stock.domain.excessoes;

public class ReturnNullException extends RuntimeException{

    public ReturnNullException(String message) {
        super(message);
    }
}
