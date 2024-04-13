package com.example.login.repository;

import com.example.login.entity.SessionsEntity;
import com.example.login.util.SessionsInterface;
import com.example.login.util.sesiones.UsuarioInterfa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionssRepository extends JpaRepository<SessionsEntity,Integer> {
    List<SessionsEntity> findAllByUsuariosIdUsuario(int usuariosIdUsuario);
    SessionsEntity findByUsuariosIdUsuarioAndFechaCierreIsNull(int usuariosIdUsuario);
    @Query(value = "update sessions set \"fechaCierre\"=now() where \"fechaCierre\" is null and \"usuarios_idUsuario\"=?1 returning \"usuarios_idUsuario\" ",nativeQuery = true)
    void registrarcierre(Integer id_usuario);

    @Query(value = "select s from SessionsEntity s ",nativeQuery = false)
    List<SessionsInterface> findAllByusuarioInterfa();

}
