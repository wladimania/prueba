package com.example.login.util;

import java.util.List;

public interface UsuarioDetails {
        Integer getIdUsuario();
        String getUserName();
        String getMail();
        String getStatus();
        Integer getIntentoFallido();
        List<RolUsuariosInterface> getRolUsuariosByIdUsuario();
        PersonaInterface getPersonaByPersonaIdPersona2();
}


