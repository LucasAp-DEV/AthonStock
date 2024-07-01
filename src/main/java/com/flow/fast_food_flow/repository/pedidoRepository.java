package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pedidoRepository extends JpaRepository<Pedido, Long> {
}
