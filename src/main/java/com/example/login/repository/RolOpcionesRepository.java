package com.example.login.repository;

import com.example.login.entity.RolOpcionesEntity;
import com.example.login.entity.RolRolOpcionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolOpcionesRepository extends JpaRepository<RolOpcionesEntity,Integer> {
    List<RolOpcionesEntity> findAllByIdOpcion(Integer idOpcion);
}
