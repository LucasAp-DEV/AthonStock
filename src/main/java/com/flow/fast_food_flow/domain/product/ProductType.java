package com.flow.fast_food_flow.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    KG("KG"),
    UN("UN");

    private final String value;
}
