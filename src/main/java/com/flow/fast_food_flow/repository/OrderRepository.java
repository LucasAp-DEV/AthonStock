package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.pedido.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
