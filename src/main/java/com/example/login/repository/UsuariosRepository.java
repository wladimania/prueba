package com.example.login.repository;

import com.example.login.entity.UsuariosEntity;
import com.example.login.util.UsuarioDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Integer> {
    boolean existsByUserName(String userName);
    Optional<UsuariosEntity> findByMail(String mail);
    boolean existsByMail(String mail);

    @Query("SELECT u FROM UsuariosEntity u WHERE (u.userName=?1 OR u.mail=?1) AND u.status='Activo'")
    UsuariosEntity buscarusuario(String usuario);

    @Query("SELECT u FROM UsuariosEntity u WHERE u.idUsuario=?1  AND u.status='Activo'")
    UsuarioDetails buscarusuarioid(Integer idusuario);


    @Query("SELECT r.rolName FROM RolUsuariosEntity ru JOIN ru.rolByRolIdRol r WHERE ru.usuariosIdUsuario = :idUsuario")
    List<String> findRolNamesByUsuarioId(@Param("idUsuario") Integer idUsuario);



}
