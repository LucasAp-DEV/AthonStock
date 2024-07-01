package com.flow.fast_food_flow.domain.excessoes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }
}
