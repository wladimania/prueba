package com.example.login.util;

import java.util.List;

public class UsuarioDetailsImpl implements UsuarioDetails {
    private Integer idUsuario;
    private String userName;
    private String mail;
    private List<RolUsuariosInterface> rolUsuariosByIdUsuario;
    private PersonaInterface personaByPersonaIdPersona2;

    // Constructor completo
    public UsuarioDetailsImpl(Integer idUsuario, String userName, String mail,
                              List<RolUsuariosInterface> rolUsuariosByIdUsuario,
                              PersonaInterface personaByPersonaIdPersona2) {
        this.idUsuario = idUsuario;
        this.userName = userName;
        this.mail = mail;
        this.rolUsuariosByIdUsuario = rolUsuariosByIdUsuario;
        this.personaByPersonaIdPersona2 = personaByPersonaIdPersona2;
    }

    // Implementación de los métodos de la interfaz UsuarioDetails
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

    public void setRolUsuariosByIdUsuario(List<RolUsuariosInterface> rolUsuariosByIdUsuario) {
        this.rolUsuariosByIdUsuario = rolUsuariosByIdUsuario;
    }

    public void setPersonaByPersonaIdPersona2(PersonaInterface personaByPersonaIdPersona2) {
        this.personaByPersonaIdPersona2 = personaByPersonaIdPersona2;
    }
}
