package com.system.athon_stock.domain.product;

public record RegisterProductDTO(String name, Long personId,Integer quantity, Float price, String marca, Float lucro, String code) {
}

