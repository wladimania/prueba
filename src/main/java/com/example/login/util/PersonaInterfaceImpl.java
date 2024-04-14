package com.example.login.util;

import java.sql.Date;

// Asegúrate de que los nombres de paquete coincidan con la estructura de tu proyecto

public class PersonaInterfaceImpl implements PersonaInterface {
    private Integer idPersona;
    private String nombres;
    private String apellido;
    private String identificacion;
    private Date fechaNacimiento;

    // Constructor
    public PersonaInterfaceImpl(Integer idPersona, String nombres, String apellido, String identificacion, Date fechaNacimiento) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
    }


    // Implementaciones de los métodos de la interfaz PersonaInterface

    @Override
    public Integer getIdPersona() {
        return this.idPersona;
    }

    @Override
    public String getNombres() {
        return this.nombres;
    }

    @Override
    public String getApellido() {
        return this.apellido;
    }

    @Override
    public String getIdentificacion() {
        return this.identificacion;
    }

    @Override
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    // Setters si son necesarios
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
