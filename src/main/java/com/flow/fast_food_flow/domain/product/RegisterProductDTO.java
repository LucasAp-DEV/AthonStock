package com.flow.fast_food_flow.domain.product;

public record RegisterProductDTO(String name, Long storeId, ProductType productType, Double weight, Integer quantity, Float price) {
}

