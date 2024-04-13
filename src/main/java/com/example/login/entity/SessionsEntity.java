package com.example.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "sessions", schema = "public", catalog = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sesiones", nullable = false)
    private Integer idSesiones;
    @Basic
    @Column(name = "fechaIngreso", nullable = true)
    private Date fechaIngreso;
    @Basic
    @Column(name = "fechaCierre", nullable = true)
    private Date fechaCierre;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarios_idUsuario", nullable = true)
    private Integer usuariosIdUsuario;
    @ManyToOne
    @JoinColumn(name = "usuarios_idUsuario", referencedColumnName = "idUsuario", nullable = true, insertable = false, updatable = false)
    private UsuariosEntity usuariosByUsuariosIdUsuario;
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionsEntity that = (SessionsEntity) o;

        if (usuariosIdUsuario != that.usuariosIdUsuario) return false;
        if (fechaIngreso != null ? !fechaIngreso.equals(that.fechaIngreso) : that.fechaIngreso != null) return false;
        if (fechaCierre != null ? !fechaCierre.equals(that.fechaCierre) : that.fechaCierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fechaIngreso != null ? fechaIngreso.hashCode() : 0;
        result = 31 * result + (fechaCierre != null ? fechaCierre.hashCode() : 0);
        result = 31 * result + usuariosIdUsuario;
        return result;
    }

}
