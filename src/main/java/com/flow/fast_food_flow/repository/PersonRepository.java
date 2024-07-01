package com.flow.fast_food_flow.repository;

import com.flow.fast_food_flow.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PersonRepository extends JpaRepository<Person, Long> {

    UserDetails findByLogin(String login);

    UserDetails findByEmail(String email);
}
