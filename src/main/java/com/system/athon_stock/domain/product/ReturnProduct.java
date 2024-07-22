package com.system.athon_stock.domain.product;

import lombok.Builder;

@Builder
public record ReturnProduct(String name, String marca, Integer quantity, Boolean status, String code) {
}
