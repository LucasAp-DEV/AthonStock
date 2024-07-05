package com.flow.fast_food_flow.domain.person;

import lombok.Builder;

@Builder
public record ResponsePersonDTO(String login, String name, String email, String phone, UserRole role) {
}
