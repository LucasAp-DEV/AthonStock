package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
