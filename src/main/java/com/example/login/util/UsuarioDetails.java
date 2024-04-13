package com.example.login.util;



import java.util.List;

public interface UsuarioDetails {
        Integer getIdUsuario();
        String getUserName();
        String getMail();
        List<RolUsuariosInterface> getRolUsuariosByIdUsuario();
        PersonaInterface getPersonaByPersonaIdPersona2();

    }
