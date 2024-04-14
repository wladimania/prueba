package com.example.login.util;

import java.util.List;

public class UsuarioDetailsImpl implements UsuarioDetails {
    private Integer idUsuario;
    private String userName;
    private String mail;
    private String status;
    private Integer intentoFallido;
    private List<RolUsuariosInterface> rolUsuariosByIdUsuario;
    private PersonaInterface personaByPersonaIdPersona2;

    public UsuarioDetailsImpl(Integer idUsuario, String userName, String mail, String status, Integer intentoFallido,
                              List<RolUsuariosInterface> rolUsuariosByIdUsuario, PersonaInterface personaByPersonaIdPersona2) {
        this.idUsuario = idUsuario;
        this.userName = userName;
        this.mail = mail;
        this.status = status;
        this.intentoFallido = intentoFallido;
        this.rolUsuariosByIdUsuario = rolUsuariosByIdUsuario;
        this.personaByPersonaIdPersona2 = personaByPersonaIdPersona2;
    }


    @Override
    public Integer getIdUsuario() {
        return idUsuario;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public Integer getIntentoFallido() {
        return intentoFallido;
    }


    @Override
    public List<RolUsuariosInterface> getRolUsuariosByIdUsuario() {
        return rolUsuariosByIdUsuario;
    }

    @Override
    public PersonaInterface getPersonaByPersonaIdPersona2() {
        return personaByPersonaIdPersona2;
    }

    // Los siguientes setters no son parte de la interfaz proporcionada, pero están aquí por completitud
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIntentoFallido(Integer intentoFallido) {
        this.intentoFallido = intentoFallido;
    }

    public void setRolUsuariosByIdUsuario(List<RolUsuariosInterface> rolUsuariosByIdUsuario) {
        this.rolUsuariosByIdUsuario = rolUsuariosByIdUsuario;
    }

    public void setPersonaByPersonaIdPersona2(PersonaInterface personaByPersonaIdPersona2) {
        this.personaByPersonaIdPersona2 = personaByPersonaIdPersona2;
    }
}
