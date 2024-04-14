package com.example.login.util;

import com.example.login.entity.RolEntity;

public class RolInterfaceImpl implements RolInterface {
    private Integer idRol;
    private String rolName;

    public RolInterfaceImpl(RolEntity rolEntity) {
        this.idRol = rolEntity.getIdRol();
        this.rolName = rolEntity.getRolName();
    }

    // Implementa los métodos definidos en la interfaz RolInterface
    @Override
    public Integer getIdRol() {
        return idRol;
    }

    @Override
    public String getRolName() {
        return rolName;
    }

    // Y otros métodos si los hay...
}
