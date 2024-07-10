package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.store.Store;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByCnpj(String cnpj);

    @NotNull
    Optional<Store> findById(Long id);

    @NotNull
    List<Store> findByPerson_Id(Long id);
}
