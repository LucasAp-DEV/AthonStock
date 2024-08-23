package com.system.athon_stock.domain.contrato;

import lombok.Builder;

@Builder
public record ContratoItensDTO(Long id,String name, Integer quantity) {
}
