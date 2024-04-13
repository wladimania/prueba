package com.example.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol", schema = "public", catalog = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol", nullable = false)
    private Integer idRol;
    @Basic
    @Column(name = "rolName", nullable = true, length = 20)
    private String rolName;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolEntity rolEntity = (RolEntity) o;

        if (idRol != rolEntity.idRol) return false;
        if (rolName != null ? !rolName.equals(rolEntity.rolName) : rolEntity.rolName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRol;
        result = 31 * result + (rolName != null ? rolName.hashCode() : 0);
        return result;
    }
}
