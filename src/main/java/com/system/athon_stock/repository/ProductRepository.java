package com.system.athon_stock.repository;

import com.system.athon_stock.domain.product.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @NotNull
    Optional<Product> findById(Long id);

    @NotNull
    Optional<Product> findByCode(String name);

    List<Product> findByPerson_id(Long id);

    List<Product> findByPerson_idAndQuantity(Long id, int quantity);

    @Query("SELECT p FROM product p WHERE p.person.id = :id AND p.quantity BETWEEN :primeiroValor AND :segundoValor")
    List<Product> findByPerson_idAndQuantityBetween(@Param("id") Long id, @Param("primeiroValor") Integer primeiroValor, @Param("segundoValor") Integer segundoValor);

}
