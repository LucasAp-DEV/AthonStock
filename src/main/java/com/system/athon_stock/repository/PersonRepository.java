package com.system.athon_stock.repository;

import com.system.athon_stock.domain.person.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    UserDetails findByLogin(String login);

    @Query("SELECT p FROM Person p WHERE p.login = :login")
    Person findByLoginUser(@Param("login") String login);

    Optional<Person> findByLoginAndId(String login, int id);

    UserDetails findByPhone(String phone);

    UserDetails findByEmail(String email);

    @NotNull
    Optional<Person> findById(Long id);

}
