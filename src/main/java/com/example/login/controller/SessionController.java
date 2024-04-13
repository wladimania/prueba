package com.example.login.controller;

import com.example.login.entity.UsuariosEntity;
import com.example.login.services.RolServices;
import com.example.login.services.SessionsServices;
import com.example.login.util.CambioContrasenaDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/session")
@CrossOrigin
public class SessionController {
    @Autowired
    private SessionsServices sessionsServices;
    @Autowired
    private RolServices rolServices;

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity <?> login(@RequestBody UsuariosEntity usuario) {
        try {
           // sessionsServices.iniciarSesion(usuario);
            return ResponseEntity.ok().body(sessionsServices.iniciarSesion(usuario));
        } catch (Exception e) {
            return new ResponseEntity<>("Error al iniciar sesión: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para cerrar sesión
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestBody UsuariosEntity usuario) {
        try {
            String result = sessionsServices.cerrarSesion(usuario);
            Map<String, String> response = new HashMap<>();
            response.put("message", result);
            return ResponseEntity.accepted().body(response); // Devuelve 202 y el mensaje en formato JSON
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al cerrar sesión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/validaRol")
    public ResponseEntity<?> validausuario(@QueryParam("idUsuario")Integer idUsuario){
        boolean validaruser= rolServices.validarAdmin(idUsuario);
        if (validaruser){
            sessionsServices.usuarioInter();
            return new ResponseEntity<>(sessionsServices.usuarioInter(),HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Error al validar Rol ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/cambiarContrasena")
    public ResponseEntity<?> cambiarContrasena(@RequestBody CambioContrasenaDTO cambioContrasenaDTO) {
        boolean resultado = sessionsServices.cambiarContrasena(cambioContrasenaDTO.getMail(), cambioContrasenaDTO.getNuevaContrasena());
        if (resultado) {
            return ResponseEntity.ok().body("Contraseña actualizada correctamente.");
        } else {
            return ResponseEntity.badRequest().body("No se encontró un usuario con el correo proporcionado.");
        }
    }

}

