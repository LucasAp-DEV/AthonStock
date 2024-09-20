package com.system.athon_stock.domain.contrato;

import com.system.athon_stock.domain.product.PriceProduct;
import lombok.Builder;

import java.util.Optional;

@Builder
public record ContratoItensDTO(Long id, String name, Integer quantity, Float priceSale, Float priceCustoProduct) {
}
