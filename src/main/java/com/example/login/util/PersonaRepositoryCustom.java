package com.example.login.util;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositoryCustom {
    @Query(value = "SELECT p.nombres || ' ' || p.apellido FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona2 WHERE u.identificacion = :identificacion", nativeQuery = true)
    String findConcatenatedNamesByIdentificacion(@Param("identificacion") String identificacion);
}