package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.product.PriceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceProductRepository extends JpaRepository<PriceProduct, Long> {
}
