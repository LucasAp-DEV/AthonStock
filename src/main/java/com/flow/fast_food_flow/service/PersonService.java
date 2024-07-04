package com.flow.fast_food_flow.service;

import com.flow.fast_food_flow.domain.excessoes.LoginPersonException;
import com.flow.fast_food_flow.domain.excessoes.RegisterPersonException;
import com.flow.fast_food_flow.domain.person.*;
import com.flow.fast_food_flow.infra.TokenService;
import com.flow.fast_food_flow.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ResponseEntity<?> loginPerson(AuthenticationDTO data) {
        if(data.login() == null){
            throw new LoginPersonException("Necessario insesir um login");
        }
        if(data.password() == null){
            throw new LoginPersonException("Necessario insesir uma senha");
        }
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        try {
            if (returnLogin(data.login()) == null)
                throw new LoginPersonException("Usuario nao existe no sistema");
            var authenticate = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Person) authenticate.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
        }catch (BadCredentialsException e) {
            throw new LoginPersonException("Credenciais Invalidas");
        }
    }

    public void registerPerson(RegisterPersonDTO data) {
        if (returnLogin(data.login()) != null)
            throw new RegisterPersonException("Login ja esta em uso");
        if (returnEmail(data.email()) != null)
            throw new RegisterPersonException("Email ja esta em uso");
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Person person = new Person(data.login(), encryptedPassword, data.email(), data.name(), data.phone(), data.role());
        personRepository.save(person);
    }

    public ResponseEntity<LoginResponseDTO> updatePerson(Long id, Person person) {
        try {
            var userID = returnId(id);
            userID.setLogin(person.getLogin());
            userID.setEmail(person.getEmail());
            userID.setName(person.getName());
            userID.setPhone(person.getPhone());
            personRepository.save(userID);

            var usernamePassword = new UsernamePasswordAuthenticationToken(person.getLogin(), person.getPassword());
            var authenticate = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Person) authenticate.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
        } catch (Exception e) {
            throw new LoginPersonException("Nao foi possivel autenticar");
        }
    }


    public UserDetails returnLogin(String login) {
        return personRepository.findByLogin(login);
    }
    public UserDetails returnEmail(String email) {
        return personRepository.findByEmail(email);
    }
    public Person returnId(Long id) {
        return personRepository.findById(id).orElseThrow(()-> new RegisterPersonException("Usuario não encontrado"));
    }
}
