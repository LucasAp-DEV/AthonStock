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

    @GetMapping("/{id}")
    public ResponsePersonDTO returnPersonId(@PathVariable(value = "id")Long id) {
        return personService.returnPersonId(id);
    }

    @PostMapping("/register")
    public void registerPerson(@RequestBody RegisterPersonDTO data) {
        personService.registerPerson(data);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPerson(@RequestBody AuthenticationDTO data) {
        return personService.loginPerson(data);
    }

    @PutMapping("/update/{id}")
    public void updatePerson(@PathVariable(value = "id")Long id, @RequestBody UpdatePersonDTO data) {
        personService.updatePerson(id, data);
    }
}
