package com.system.athon_stock.domain.contrato;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ContratoResponseDTO(Long id,
                                  String description,
                                  LocalDate date,
                                  String nameClient,
                                  Float totalValueContrato,
                                  Float labor,
                                  List<ContratoItensDTO> contratoItens) {
}