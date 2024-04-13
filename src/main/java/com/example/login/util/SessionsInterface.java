package com.example.login.util;

import com.example.login.util.sesiones.UsuarioInterfa;

import java.sql.Date;

public interface SessionsInterface {
    Integer getIdSesiones();
    Date getFechaIngreso();
    Date getFechaCierre();
    UsuarioInterfa getUsuariosByUsuariosIdUsuario();
}