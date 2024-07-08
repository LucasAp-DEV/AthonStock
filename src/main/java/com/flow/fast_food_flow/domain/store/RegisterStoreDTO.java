package com.flow.fast_food_flow.domain.store;

import com.flow.fast_food_flow.domain.person.Person;

public record RegisterStoreDTO(String name, String cnpj, String address, Person userId) {

}
