package com.system.athon_stock.domain.contrato;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ContratoResponseDTO(Long id,
                                  String description,
                                  LocalDate date,
                                  String nameClient) {
}