package com.example.login.repository;

import com.example.login.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<RolEntity,Integer> {

    @Query("SELECT r FROM RolEntity r WHERE r.idRol = CAST(:idRol AS integer)")
    List<RolEntity> findAllByIdRol(@Param("idRol") String idRol);

    @Query(value = "select r.\"rolName\" from rol_usuarios ru INNER JOIN rol r on ru.\"rol_idRol\" = r.\"idRol\" where \"usuarios_idUsuario\"=?1",nativeQuery = true)
    List<String> findAllByIdRolasd(Integer idUsuario);
}
