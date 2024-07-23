package com.system.athon_stock.controller;

import com.system.athon_stock.domain.person.*;
import com.system.athon_stock.service.PersonService;
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
    public ResponseEntity<LoginResponseDTO> loginPerson(@RequestBody AuthenticationDTO data) {
        return personService.loginPerson(data);
    }

    @PutMapping("/update/{id}")
    public void updatePerson(@PathVariable(value = "id")Long id, @RequestBody UpdatePersonDTO data) {
        personService.updatePerson(id, data);
    }
}
