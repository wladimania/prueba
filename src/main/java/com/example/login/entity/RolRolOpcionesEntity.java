package com.example.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol_rolOpciones", schema = "public", catalog = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolRolOpcionesEntity {
    @Basic
    @Column(name = "rol_idRol", nullable = true,insertable = false, updatable = false)

    private Integer rolIdRol;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rolOpciones_idOpciones", nullable = false)
    private Integer rolOpcionesIdOpciones;
    @ManyToOne
    @JoinColumn(name = "rol_idRol", referencedColumnName = "idRol",insertable = false, updatable = false)
    private RolEntity rolByRolIdRol;

    @OneToOne
    @JoinColumn(name = "rolOpciones_idOpciones", referencedColumnName = "idOpcion", nullable = false)
    private RolOpcionesEntity rolOpcionesByRolOpcionesIdOpciones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolRolOpcionesEntity that = (RolRolOpcionesEntity) o;

        if (rolOpcionesIdOpciones != that.rolOpcionesIdOpciones) return false;
        if (rolIdRol != null ? !rolIdRol.equals(that.rolIdRol) : that.rolIdRol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rolIdRol != null ? rolIdRol.hashCode() : 0;
        result = 31 * result + rolOpcionesIdOpciones;
        return result;
    }

    public RolEntity getRolByRolIdRol() {
        return rolByRolIdRol;
    }

    public void setRolByRolIdRol(RolEntity rolByRolIdRol) {
        this.rolByRolIdRol = rolByRolIdRol;
    }

    public RolOpcionesEntity getRolOpcionesByRolOpcionesIdOpciones() {
        return rolOpcionesByRolOpcionesIdOpciones;
    }

    public void setRolOpcionesByRolOpcionesIdOpciones(RolOpcionesEntity rolOpcionesByRolOpcionesIdOpciones) {
        this.rolOpcionesByRolOpcionesIdOpciones = rolOpcionesByRolOpcionesIdOpciones;
    }
}
