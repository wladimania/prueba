package com.example.login.repository;

import com.example.login.entity.RolRolOpcionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RolRolOpcionesRepository extends JpaRepository<RolRolOpcionesEntity,Integer> {
    @Query("SELECT r FROM RolRolOpcionesEntity r WHERE r.rolByRolIdRol.idRol = CAST(:idRol AS integer)")
    List<RolRolOpcionesEntity> findAllByRolByRolIdRol(@Param("idRol") String idRol);

}
