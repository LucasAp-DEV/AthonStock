package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByCnpj(String cnpj);

    Optional<Store> findById(Long id);
}
