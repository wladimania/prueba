package com.example.login.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "usuarios", schema = "public", catalog = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;

    @NotBlank(message = "El nombre de usuario no puede estar en blanco")
    @Size(min = 8, max = 20, message = "La longitud del nombre de usuario debe estar entre 8 y 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "El nombre de usuario no debe contener signos")
    @UniqueElements(message = "El nombre de usuario ya está en uso")
    @Pattern(regexp = ".*[0-9].*", message = "El nombre de usuario debe contener al menos un número")
    @Pattern(regexp = ".*[A-Z].*", message = "El nombre de usuario debe contener al menos una letra mayúscula")
    @Basic
    @Column(name = "user_name", nullable = false, length = 50, unique = true)
    private String userName;

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Basic
    @Column(name = "mail", nullable = true, length = 120)
    private String mail;

    @Basic
    @Column(name = "session_active", nullable = true, length = -1)
    private String sessionActive;

    @Basic
    @Column(name = "persona_id_persona2", nullable = true, insertable = false, updatable = false)
    private Integer personaIdPersona2;

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    private String status;

    @Column(name = "intento_fallido", columnDefinition = "int default 0")
    private Integer intentoFallido;

    @OneToMany(mappedBy = "usuariosByUsuariosIdUsuario")
    private List<RolUsuariosEntity> rolUsuariosByIdUsuario;

    @OneToMany(mappedBy = "usuariosByUsuariosIdUsuario")
    private List<SessionsEntity> sessionsByIdUsuario;

    @ManyToOne
    @JoinColumn(name = "persona_id_persona2", referencedColumnName = "id_persona")
    private PersonaEntity personaByPersonaIdPersona2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuariosEntity that = (UsuariosEntity) o;

        if (idUsuario != null ? !idUsuario.equals(that.idUsuario) : that.idUsuario != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (sessionActive != null ? !sessionActive.equals(that.sessionActive) : that.sessionActive != null)
            return false;
        if (personaIdPersona2 != null ? !personaIdPersona2.equals(that.personaIdPersona2) : that.personaIdPersona2 != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return intentoFallido != null ? intentoFallido.equals(that.intentoFallido) : that.intentoFallido == null;
    }

    @Override
    public int hashCode() {
        int result = idUsuario != null ? idUsuario.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (sessionActive != null ? sessionActive.hashCode() : 0);
        result = 31 * result + (personaIdPersona2 != null ? personaIdPersona2.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (intentoFallido != null ? intentoFallido.hashCode() : 0);
        return result;
    }

    public String generarCorreo() {
        if (personaByPersonaIdPersona2 != null) {
            String apellido = personaByPersonaIdPersona2.getApellido().toLowerCase().trim();
            String apellido2 = "";
            if (apellido.contains(" ")) {
                String[] apellidovect = apellido.split(" ");
                apellido2 = apellidovect[0];
                if (apellidovect[1].length() > 1) {
                    apellido2 += apellidovect[1].substring(0, 1);
                }
            }
            return userName.substring(0, 1).toLowerCase() + apellido2 + "@gmail.com";
        }
        return null;
    }
}
