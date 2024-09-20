package com.system.athon_stock.repository;

import com.system.athon_stock.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByPerson_id(Long id);

    List<Contrato> findByPerson_IdAndDateBetween(Long id, LocalDate dataInicio, LocalDate dataFim);

}
