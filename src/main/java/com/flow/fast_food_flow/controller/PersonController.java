package com.flow.fast_food_flow.controller;

import com.flow.fast_food_flow.domain.person.AuthenticationDTO;
import com.flow.fast_food_flow.domain.person.RegisterPersonDTO;
import com.flow.fast_food_flow.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/register")
    public void registerPerson(@RequestBody @Valid RegisterPersonDTO data) {
        personService.registerPerson(data);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPerson(@RequestBody AuthenticationDTO data) {
        return personService.loginPerson(data.login(), data.password());
    }
}
