package com.example.login.controller;

import com.example.login.entity.UsuariosEntity;
import com.example.login.services.UsuariosServices;
import com.example.login.util.UsuarioDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("api/usuarios")
@CrossOrigin
public class UsuarioController {
    @Autowired
    private  UsuariosServices usuariosServices;

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody UsuariosEntity usuario) {
        try {
            usuariosServices.createUser(usuario);
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/valida")
    public ResponseEntity<String> validausuario(@QueryParam("identificacion")String identificacion){
        boolean validaruser= usuariosServices.existePersona(identificacion);
        if (validaruser){
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Error al crear el usuario: Identificación ya registrada ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/usuarios/{idUsuario}")
    public ResponseEntity<?> getUsuariosIfAdmin(@PathVariable("idUsuario")Integer idUsuario) {
        try {
            List<UsuarioDetails> usuarios = usuariosServices.findAllUsuariosIfAdmin(idUsuario);
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener usuarios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
