package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.receita.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Revenue, Long> {
}
