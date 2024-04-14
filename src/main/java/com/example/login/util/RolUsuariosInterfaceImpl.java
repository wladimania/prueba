package com.example.login.util;

import com.example.login.entity.RolUsuariosEntity;
import com.example.login.entity.RolEntity;

public class RolUsuariosInterfaceImpl implements RolUsuariosInterface {
    private Integer rolIdRol;
    private RolEntity rol;

    public RolUsuariosInterfaceImpl(Integer rolIdRol, RolEntity rol) {
        this.rolIdRol = rolIdRol;
        this.rol = rol;
    }

    @Override
    public Integer getRolIdRol() {
        return rolIdRol;
    }

    @Override
    public RolInterface getRolByRolIdRol() {
        // Suponemos que RolInterface es una interfaz y tienes una implementación para ella también.
        // Puedes hacer algo similar a lo que hicimos aquí con RolUsuariosInterfaceImpl.
        return new RolInterfaceImpl(this.rol);
    }
}
