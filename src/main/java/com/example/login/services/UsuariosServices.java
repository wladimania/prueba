package com.example.login.services;

import com.example.login.entity.PersonaEntity;
import com.example.login.entity.UsuariosEntity;
import com.example.login.repository.PersonaRepository;
import com.example.login.repository.UsuariosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UsuariosServices {
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private PersonaRepository personaRepository;

    public UsuariosEntity createUser(UsuariosEntity usuario) {
        validateUserName(usuario.getUserName());
        validatePassword(usuario.getPassword());
        usuario.setIntentoFallido(0);
        usuario.setStatus("Activo");
        String correo = usuario.generarCorreo().trim();
        usuario.setMail(correo);
        validateCorreo(usuario);
        PersonaEntity nuevaPersona = usuario.getPersonaByPersonaIdPersona2();
        validateIdentification(nuevaPersona.getIdentificacion());
        if (nuevaPersona != null) {
            nuevaPersona = personaRepository.save(nuevaPersona);
            usuario.setPersonaByPersonaIdPersona2(nuevaPersona);
        }
        return usuariosRepository.save(usuario);
    }

    public boolean existePersona(String identificacion){
     return personaRepository.existsByIdentificacion(identificacion);
    }
    private void validateUserName(String userName) {
        if (!isValidUserNameSignos(userName)) {
            throw new IllegalArgumentException("El nombre de usuario no debe tener signos.");
        }
        if (!isValidUserNamenumeros(userName)) {
            throw new IllegalArgumentException("El nombre de usuario debe tener al menos un número.");
        }
        if (!isValidUserNameMayuscula(userName)) {
            throw new IllegalArgumentException("El nombre de usuario debe tener al menos una letra mayuscula.");
        }
        if (!isValidUserNameTamaño(userName)) {
            throw new IllegalArgumentException("El nombre de usuario debe tener entre 8 y 20 caracteres.");
        }
        if (usuariosRepository.existsByUserName(userName)) {
            int contador = 1;
            String nuevoUserName = userName + contador;
            while (usuariosRepository.existsByUserName(nuevoUserName)) {
                contador++;
                nuevoUserName = userName + contador;
            }

            userName = nuevoUserName;
            throw new IllegalArgumentException("El nombre de usuario ya existe, se agrego un numero adiciona.");

        }
    }

    private void validateCorreo(UsuariosEntity usuario) {
        String correo = usuario.getMail();
        if (usuariosRepository.existsByMail(correo)) {
            int contadorCorreo = 1;
            String nuevoCorreo = correo;
            while (usuariosRepository.existsByMail(nuevoCorreo)) {
                contadorCorreo++;
                nuevoCorreo = correo.replaceFirst("@", contadorCorreo + "@");
            }
            usuario.setMail(nuevoCorreo);
        }
    }


    private boolean isValidUserNameSignos(String userName) {
        if (!userName.matches("^[a-zA-Z0-9]+$")) {
            return false;
        }
        return true;
    }
    private boolean isValidUserNamenumeros(String userName) {
        if (!userName.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }
    private boolean isValidUserNameMayuscula(String userName) {
             if (!userName.matches(".*[A-Z].*")) {
            return false;
        }
        return true;
    }
    private boolean isValidUserNameTamaño(String userName) {
        if (userName.length() < 8 || userName.length() > 20) {
            return false;
        }
        return true;
    }

    private String generateCorreo(String nombres, String apellidos) {
        return nombres.toLowerCase().charAt(0) + apellidos.toLowerCase() + "@gmail.com";
    }
    private String generateUserName(String nombres, String apellidos) {
        return nombres.toLowerCase().charAt(0) + apellidos.toLowerCase();
    }
    private String generatePassword() {
        return "contraseñaGenerada";
    }
    private void validatePassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos.");
        }
    }
    private boolean isValidPassword(String password) {
        // a. Debe tener al menos 8 dígitos
        if (password.length() < 8) {
            return false;
        }
        // b. Debe tener al menos una letra mayúscula
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // c. No debe contener espacios
        if (password.contains(" ")) {
            return false;
        }
        // d. Debe tener al menos un signo
        if (!password.matches("^(?=.*[!@#$%^&()-+=]).+$")) {
            return false;
        }
        // Si todas las condiciones son verdaderas, la contraseña es válida
        return true;
    }
    private void validateIdentification(String identification) {
        // Verificar que la identificación cumple con las restricciones
        if (!isValidIdentification(identification)) {
            throw new IllegalArgumentException("La identificación no cumple con los requisitos.");
        }
        if (personaRepository.existsByIdentificacion(identification)){
            throw new IllegalArgumentException("Ya existe la identificación ingresasda.");
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

    public void actualizarUsuario(UsuariosEntity usuario) {
        usuariosRepository.save(usuario);
    }
}
