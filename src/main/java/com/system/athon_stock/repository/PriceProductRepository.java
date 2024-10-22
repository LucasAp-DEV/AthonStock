package com.system.athon_stock.repository;

import com.system.athon_stock.domain.product.PriceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceProductRepository extends JpaRepository<PriceProduct, Long> {
    Optional<PriceProduct> findFirstByProduct_IdOrderByIdDesc(Long id);
}
