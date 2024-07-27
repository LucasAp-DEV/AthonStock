package com.system.athon_stock.domain.product;

import lombok.Builder;


@Builder
public record ReturnProduct(Long id, String name, String marca, Integer quantity, Boolean status, String code, Float price,
                            Float lucro, Float priceSale) {
}
