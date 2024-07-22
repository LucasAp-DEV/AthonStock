package com.system.athon_stock.repository;

import com.system.athon_stock.domain.person.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    UserDetails findByLogin(String login);

    UserDetails findByEmail(String email);

    @NotNull
    Optional<Person> findById(Long id);
}
