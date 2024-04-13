package com.example.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rolOpciones", schema = "public", catalog = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolOpcionesEntity {
    @Basic
    @Column(name = "nombreOpcion", nullable = false)
    private Integer nombreOpcion;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idOpcion", nullable = false)
    private Integer idOpcion;
    @OneToOne(mappedBy = "rolOpcionesByRolOpcionesIdOpciones")
    private RolRolOpcionesEntity rolRolOpcionesByIdOpcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolOpcionesEntity that = (RolOpcionesEntity) o;

        if (nombreOpcion != that.nombreOpcion) return false;
        if (idOpcion != that.idOpcion) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nombreOpcion;
        result = 31 * result + idOpcion;
        return result;
    }

    public RolRolOpcionesEntity getRolRolOpcionesByIdOpcion() {
        return rolRolOpcionesByIdOpcion;
    }

    public void setRolRolOpcionesByIdOpcion(RolRolOpcionesEntity rolRolOpcionesByIdOpcion) {
        this.rolRolOpcionesByIdOpcion = rolRolOpcionesByIdOpcion;
    }
}
