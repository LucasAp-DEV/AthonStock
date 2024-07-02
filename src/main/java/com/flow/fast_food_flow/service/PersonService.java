package com.flow.fast_food_flow.service;

import com.flow.fast_food_flow.domain.excessoes.LoginPersonException;
import com.flow.fast_food_flow.domain.person.LoginPersonDTO;
import com.flow.fast_food_flow.domain.person.LoginResponseDTO;
import com.flow.fast_food_flow.domain.person.Person;
import com.flow.fast_food_flow.domain.person.RegisterPersonDTO;
import com.flow.fast_food_flow.infra.TokenService;
import com.flow.fast_food_flow.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ResponseEntity<LoginResponseDTO> loginPerson(LoginPersonDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Person) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    public void registerPerson(RegisterPersonDTO data) {
        if (returnLogin(data.login()) != null)
            throw new LoginPersonException("Login ja esta em uso");
        if (returnEmail(data.email()) != null)
            throw new LoginPersonException("Email ja esta em uso");
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Person person = new Person(data.login(), encryptedPassword, data.email(), data.name(), data.phone(), data.role());
        personRepository.save(person);
    }

    public UserDetails returnLogin(String login) {
        return personRepository.findByLogin(login);
    }
    public UserDetails returnEmail(String email) {
        return personRepository.findByEmail(email);
    }
}
