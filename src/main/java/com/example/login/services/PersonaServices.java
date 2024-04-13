package com.example.login.services;

import com.example.login.controller.PersonaController;
import com.example.login.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PersonaServices {
    @Autowired
    private final PersonaController personaController;

    @Autowired
    @Lazy
    public PersonaServices(PersonaController personaController) {
        this.personaController = personaController;
    }
    private void validateIdentification(String identification) {
        // Verificar que la identificación cumple con las restricciones
        if (!isValidIdentification(identification)) {
            throw new IllegalArgumentException("La identificación no cumple con los requisitos.");
        }
    }

    private boolean isValidIdentification(String identification) {
        // a. Debe tener 10 dígitos
        if (identification.length() != 10) {
            return false;
        }
        // b. Solo números
        if (!identification.matches("\\d+")) {
            return false;
        }
        // c. Validar que no tenga 4 veces seguidas un número
        if (identification.matches(".*(\\d)\\1{3,}.*")) {
            return false;
        }
        // Si todas las condiciones son verdaderas, la identificación es válida
        return true;
    }
}
