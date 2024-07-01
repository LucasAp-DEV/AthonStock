package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, Long> {
}
