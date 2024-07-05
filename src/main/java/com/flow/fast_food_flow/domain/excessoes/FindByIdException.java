package com.flow.fast_food_flow.domain.excessoes;

public class FindByIdException extends RuntimeException {
    public FindByIdException(String message) {
        super(message);
    }
}
