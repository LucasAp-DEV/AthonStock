package com.system.athon_stock.domain.contrato;

import java.util.List;

public record UpdateContratoDTO(Long personId, String description, Float labor, String nameClient, List<ProductListContrato> products) {

}
