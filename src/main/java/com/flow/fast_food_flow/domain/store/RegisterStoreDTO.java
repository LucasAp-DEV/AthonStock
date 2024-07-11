package com.flow.fast_food_flow.domain.store;

public record RegisterStoreDTO(String name, String cnpj, String address,Boolean status, Long personId) {

}