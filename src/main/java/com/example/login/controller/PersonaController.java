package com.example.login.controller;

import com.example.login.services.PersonaServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/personas")
@CrossOrigin
public class PersonaController {
    @Autowired
    private PersonaServices personaServices;
    @Autowired
    @Lazy
    public PersonaController(PersonaServices personaServices) {
        this.personaServices = personaServices;
    }

}
