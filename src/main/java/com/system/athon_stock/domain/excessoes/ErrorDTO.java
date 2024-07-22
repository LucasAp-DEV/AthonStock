package com.system.athon_stock.domain.excessoes;

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
