package com.system.athon_stock.domain.person;

import lombok.Builder;

@Builder
public record ResponsePersonDTO(String login, String name, String email, String phone, UserRole role) {
}
