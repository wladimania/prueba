package com.example.login.services;

import com.example.login.entity.SessionsEntity;
import com.example.login.entity.UsuariosEntity;
import com.example.login.repository.SessionssRepository;
import com.example.login.repository.UsuariosRepository;
import com.example.login.util.SessionsInterface;
import com.example.login.util.UsuarioDetails;
import com.example.login.util.sesiones.UsuarioInterfa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SessionsServices {

    @Autowired
    private SessionssRepository sessionssRepository;

    // @Autowired
    //private UsuariosServices usuariosServices;
    private final String ESTADOSESION_ACTIVA="Activa";
    private final String ESTADOCUENTA_BLOQUEADA="Bloqueado";
    @Autowired
    private UsuariosRepository usuariosRepository;
    public UsuarioDetails iniciarSesion(UsuariosEntity usuario) {
        //usuariosRepository.buscarusuario(usuario.getUserName(),usuario.getPassword());
        UsuariosEntity Objeruseraux = usuariosRepository.buscarusuario(usuario.getUserName());
      //  System.out.println(Objeruseraux.getUserName());
        if (Objeruseraux == null) {
            throw new IllegalArgumentException("Usuario, correo no encontrado.");
        }
        if (!Objeruseraux.getPassword().equals(usuario.getPassword())) {
            Objeruseraux.setIntentoFallido(Objeruseraux.getIntentoFallido() + 1);
            usuariosRepository.save(Objeruseraux);
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }
        if (Objeruseraux.getIntentoFallido() >= 3) {
            Objeruseraux.setStatus("Bloqueado");
            usuariosRepository.save(Objeruseraux);
            throw new IllegalArgumentException("Usuario Bloqueado.");
        }
        if (ESTADOSESION_ACTIVA.equals(Objeruseraux.getSessionActive())) {
            throw new IllegalArgumentException("El usuario ya tiene una sesión activa.");
        }
        SessionsEntity sesion = new SessionsEntity();
        Date Date = new Date(System.currentTimeMillis());
        sesion.setFechaIngreso(Date);
        sesion.setUsuariosIdUsuario(Objeruseraux.getIdUsuario());
        sessionssRepository.save(sesion);
        Objeruseraux.setSessionActive(ESTADOSESION_ACTIVA);
        Objeruseraux.setStatus("Activo");
        Objeruseraux.setIntentoFallido(0);
        usuariosRepository.save(Objeruseraux);
        return usuariosRepository.buscarusuarioid(Objeruseraux.getIdUsuario());
    }

    public String cerrarSesion(UsuariosEntity usuario) {
        Optional<UsuariosEntity> objetUser = usuariosRepository.findById(usuario.getIdUsuario());
        if (objetUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario, correo no encontrado.");
        }
        UsuariosEntity Objeruseraux = objetUser.get();
        if (!ESTADOSESION_ACTIVA.equals(Objeruseraux.getSessionActive())) {
            throw new IllegalArgumentException("El usuario no tiene una sesión activa.");
        }
        Objeruseraux.setSessionActive("Inactiva");
        sessionssRepository.registrarcierre(Objeruseraux.getIdUsuario());
        usuariosRepository.save(Objeruseraux);
        return "Sesión cerrada";
    }


    public boolean cambiarContrasena(String mail, String nuevaContrasena) {
        validatePassword(nuevaContrasena);
        Optional<UsuariosEntity> usuarioOpt = usuariosRepository.findByMail(mail);
        if (usuarioOpt.isPresent()) {
            UsuariosEntity usuario = usuarioOpt.get();
            usuario.setPassword(nuevaContrasena); // Considera usar encriptación aquí
            usuariosRepository.save(usuario);
            return true;
        }
        return false;
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

    public List<SessionsInterface> usuarioInter(){
        return sessionssRepository.findAllByusuarioInterfa();
    }
}
