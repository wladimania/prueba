package com.example.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "persona", schema = "public", catalog = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_persona", nullable = false, unique = true)
    private Integer idPersona;
    @Basic
    @Column(name = "nombres", nullable = false, length = 60)
    private String nombres;
    @Basic
    @Column(name = "apellido", nullable = false, length = 60)
    private String apellido;
    @Basic
    @Column(name = "identificacion", nullable = true, length = 10, unique = true)
    private String identificacion;
    @Basic
    @Column(name = "fecha_nacimiento", nullable = false )
    private Date fechaNacimiento;
    @OneToMany(mappedBy = "personaByPersonaIdPersona2")
    private List<UsuariosEntity> usuariosByIdPersona;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonaEntity that = (PersonaEntity) o;

        if (idPersona != that.idPersona) return false;
        if (nombres != null ? !nombres.equals(that.nombres) : that.nombres != null) return false;
        if (apellido != null ? !apellido.equals(that.apellido) : that.apellido != null) return false;
        if (identificacion != null ? !identificacion.equals(that.identificacion) : that.identificacion != null)
            return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPersona;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (identificacion != null ? identificacion.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        return result;
    }

}
