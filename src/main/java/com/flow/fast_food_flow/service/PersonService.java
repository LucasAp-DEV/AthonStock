package com.flow.fast_food_flow.service;

import com.flow.fast_food_flow.domain.person.Person;
import com.flow.fast_food_flow.domain.person.RegisterPersonDTO;
import com.flow.fast_food_flow.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public ResponseEntity<?> registerPerson(RegisterPersonDTO data) {
        if (returnLogin(data.login()) != null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ja esta em uso");
        if (returnEmail(data.email()) != null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ja esta em uso");
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Person person = new Person(data.login(), encryptedPassword, data.email(), data.name(), data.phone(), data.role());
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoas Cadastrada com sucesso");
    }

    public UserDetails returnLogin(String login) {
        return personRepository.findByLogin(login);
    }
    public UserDetails returnEmail(String email) {
        return personRepository.findByEmail(email);
    }
}
