package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface storeRepository extends JpaRepository<Store, Long> {
}