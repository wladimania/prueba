package com.example.login.services;


import com.example.login.entity.RolEntity;
import com.example.login.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class RolServices {
    @Autowired
    private RolRepository rolRepository;
    public RolEntity saveRol(RolEntity rolEntity){
        return rolRepository.save(rolEntity);
    }
    public void deleteRolById(int rolId){
        rolRepository.deleteById(rolId);
    }
    public RolEntity updateRol(int id, RolEntity updatedRolEntity) {
        Optional<RolEntity> existingRolOptional = rolRepository.findById(id);
        if (existingRolOptional.isPresent()) {
            RolEntity existingRolEntity = existingRolOptional.get();
            existingRolEntity.setRolName(updatedRolEntity.getRolName());

            return rolRepository.save(existingRolEntity);
        } else {
            return new RolEntity(-1, "Error: La entidad con ID " + id + " no existe.");
        }
    }
    public boolean validarAdmin(Integer idUsuario){
        for (String rol:rolRepository.findAllByIdRolasd(idUsuario)) {
            if (rol.trim().equals("Admin")){
                return true;
            }
        }
        return false;
    }
}
