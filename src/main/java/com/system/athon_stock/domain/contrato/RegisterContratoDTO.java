package com.system.athon_stock.domain.contrato;

import java.util.List;

public record RegisterContratoDTO(String description, Float price, Long personId, List<Long> productId) {
}
