package com.system.athon_stock.repository;

import com.system.athon_stock.domain.product.PriceProduct;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceProductRepository extends JpaRepository<PriceProduct, Long> {
    @NotNull
    Optional<PriceProduct> findByProduct_Id(Long id);
}
