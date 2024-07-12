package com.system.casaroto.domain.person;

public record RegisterPersonDTO(String login, String password, String name, String email, String phone, UserRole role) {
}
