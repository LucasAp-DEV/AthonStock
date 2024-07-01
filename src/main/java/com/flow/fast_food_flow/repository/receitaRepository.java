package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.receita.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface receitaRepository extends JpaRepository<Receita, Long> {
}
