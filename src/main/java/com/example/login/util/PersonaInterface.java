package com.example.login.util;

import java.sql.Date;
import java.util.List;

public interface PersonaInterface {
    Integer getIdPersona();
    String getNombres();
    String getApellido();
    String getIdentificacion();
    Date getFechaNacimiento();
}