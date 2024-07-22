package com.system.athon_stock.repository;

import com.system.athon_stock.domain.product.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @NotNull
    Optional<Product> findById(Long id);
    @NotNull
    Optional<Product> findByCode(String name);
}
