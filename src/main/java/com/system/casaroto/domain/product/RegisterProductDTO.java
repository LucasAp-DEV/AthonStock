package com.system.casaroto.domain.product;

public record RegisterProductDTO(String name, Long storeId,Integer quantity, Float price, String marca, Float priceSale) {
}

