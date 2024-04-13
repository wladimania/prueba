package com.example.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol_usuarios", schema = "public", catalog = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolUsuariosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rol_idRol", nullable = false,insertable = false, updatable = false)
    private Integer rolIdRol;
    @Basic
    @Column(name = "usuarios_idUsuario", nullable = true)
    private Integer usuariosIdUsuario;
    @OneToOne
    @JoinColumn(name = "rol_idRol", referencedColumnName = "idRol", nullable = false,insertable = false, updatable = false)
    private RolEntity rolByRolIdRol;
    @ManyToOne
    @JoinColumn(name = "usuarios_idUsuario", referencedColumnName = "idUsuario",insertable = false, updatable = false)
    private UsuariosEntity usuariosByUsuariosIdUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolUsuariosEntity that = (RolUsuariosEntity) o;

        if (rolIdRol != that.rolIdRol) return false;
        if (usuariosIdUsuario != null ? !usuariosIdUsuario.equals(that.usuariosIdUsuario) : that.usuariosIdUsuario != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rolIdRol;
        result = 31 * result + (usuariosIdUsuario != null ? usuariosIdUsuario.hashCode() : 0);
        return result;
    }

    public RolEntity getRolByRolIdRol() {
        return rolByRolIdRol;
    }

    public void setRolByRolIdRol(RolEntity rolByRolIdRol) {
        this.rolByRolIdRol = rolByRolIdRol;
    }

    public UsuariosEntity getUsuariosByUsuariosIdUsuario() {
        return usuariosByUsuariosIdUsuario;
    }

    public void setUsuariosByUsuariosIdUsuario(UsuariosEntity usuariosByUsuariosIdUsuario) {
        this.usuariosByUsuariosIdUsuario = usuariosByUsuariosIdUsuario;
    }
}
