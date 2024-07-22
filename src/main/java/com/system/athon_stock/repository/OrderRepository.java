package com.system.athon_stock.repository;

import com.system.athon_stock.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Contrato, Long> {
}
