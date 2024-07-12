package com.system.casaroto.repository;

import com.system.casaroto.domain.pedido.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Contrato, Long> {
}
