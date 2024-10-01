package com.system.athon_stock.domain.contrato.request;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record ContratoServiceRequest(Long id, LocalDate dataInicio, LocalDate dataFim) {
}
