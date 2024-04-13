package com.example.login.repository;

import com.example.login.entity.RolEntity;
import com.example.login.entity.RolUsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolUsuariosRepository extends JpaRepository<RolUsuariosEntity, Integer> {
    List<RolUsuariosEntity> findAllByRolByRolIdRol(RolEntity rol);
}