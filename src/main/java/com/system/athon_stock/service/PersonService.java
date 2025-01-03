package com.system.athon_stock.service;

import com.system.athon_stock.domain.excessoes.CredentialsException;
import com.system.athon_stock.domain.excessoes.FindByIdException;
import com.system.athon_stock.domain.excessoes.RegisterPersonException;
import com.system.athon_stock.infra.TokenService;
import com.system.athon_stock.repository.PersonRepository;
import com.system.athon_stock.domain.person.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final LoginAttemptService loginAttemptService;

    public ResponsePersonDTO returnPersonId(Long id) {
        var personEntity = returnId(id);
        return responseBuilderDTO(personEntity);
    }

    public ResponseEntity<LoginResponseDTO> loginPerson(AuthenticationDTO data) {
        validateAutentication(data);
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        try {
            if (loginAttemptService.isBlocked(data.login())) {
                throw new CredentialsException("Usuario bloqueado devido a multiplas tentativas falhas de login");
            }
            if (returnLogin(data.login()) == null)
                throw new CredentialsException("Usuario nao existe no sistema");
            var authenticate = this.authenticationManager.authenticate(usernamePassword);
            loginAttemptService.loginSucceeded(data.login());
            var token = tokenService.generateToken((Person) authenticate.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            loginAttemptService.loginFailed(data.login());
            throw new CredentialsException("Credenciais Invalidas");
        }
    }

    public void registerPerson(RegisterPersonDTO data) {
        if (returnLogin(data.login()) != null)
            throw new RegisterPersonException("Login ja esta em uso");
        if (returnEmail(data.email()) != null)
            throw new RegisterPersonException("Email ja esta em uso");
        if (findByPhone(data.phone()) != null)
            throw new RegisterPersonException("Telefone ja esta em uso");
        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Person person = new Person(data.login(), encryptedPassword, data.email(), data.name(), data.phone(), data.role());
        person.bindRegister(data);
        personRepository.save(person);
    }

    public void updatePerson(Long id, UpdatePersonDTO person) {
        var personEntity = returnId(id);
        try {
            personEntity.bind(person);
            personRepository.save(personEntity);
        } catch (Exception e) {
            throw new CredentialsException("Nao foi possivel atualizar o usuario pois " + e.getMessage());
        }
    }

    public UserDetails returnLogin(String login) {
        return personRepository.findByLogin(login);
    }

    public UserDetails findByPhone(String phone) {
        return personRepository.findByPhone(phone);
    }

    public UserDetails returnEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person returnId(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new FindByIdException("Usuario não encontrado"));
    }

    public ResponsePersonDTO responseBuilderDTO(Person person) {
        ResponsePersonDTO.ResponsePersonDTOBuilder builder = ResponsePersonDTO.builder()
                .name(person.getName())
                .login(person.getLogin())
                .email(person.getEmail())
                .role(person.getRole())
                .phone(person.getPhone());
        return builder.build();
    }

    private void validateAutentication(AuthenticationDTO data) {
        if (Objects.isNull(data.login()))
            throw new CredentialsException("Necessario insesir um login");
        if (data.login().isBlank())
            throw new CredentialsException("Necessario inserir um login");
        if (Objects.isNull(data.password()))
            throw new CredentialsException("Necessario insesir uma senha");
        if (data.password().isBlank())
            throw new CredentialsException("Necessario inserir uma senha");
    }
}
