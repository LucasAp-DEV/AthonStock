package com.system.casaroto.repository;

import com.system.casaroto.domain.product.PriceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceProductRepository extends JpaRepository<PriceProduct, Long> {
}
