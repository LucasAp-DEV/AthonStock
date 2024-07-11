package com.flow.fast_food_flow.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    KG("kg"),
    UN("un");

    private final String value;
}
