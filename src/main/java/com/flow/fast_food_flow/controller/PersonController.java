package com.flow.fast_food_flow.controller;

import com.flow.fast_food_flow.domain.person.*;
import com.flow.fast_food_flow.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> loginPerson(@RequestBody @Valid AuthenticationDTO data) {
        return personService.loginPerson(data);
    }

    @PutMapping("/update")
    public ResponseEntity<LoginResponseDTO> updatePerson(@RequestBody @Valid Person data, @RequestBody @Valid Long id) {
        return personService.updatePerson(id, data);
    }
}
