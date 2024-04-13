package com.example.login.repository;


import com.example.login.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {
    PersonaEntity findByIdentificacion(String identificacion);
    List<PersonaEntity> findAllByIdPersona(Integer idPersona);
    boolean existsByIdentificacion(String identificacion);
}
