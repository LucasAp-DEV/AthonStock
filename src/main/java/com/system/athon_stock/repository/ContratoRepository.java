package com.system.athon_stock.repository;

import com.system.athon_stock.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByPerson_id(Long id);
}
