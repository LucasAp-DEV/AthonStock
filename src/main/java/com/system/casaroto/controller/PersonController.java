package com.system.casaroto.controller;

import com.system.casaroto.service.PersonService;
import com.system.casaroto.domain.person.AuthenticationDTO;
import com.system.casaroto.domain.person.RegisterPersonDTO;
import com.system.casaroto.domain.person.ResponsePersonDTO;
import com.system.casaroto.domain.person.UpdatePersonDTO;
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
