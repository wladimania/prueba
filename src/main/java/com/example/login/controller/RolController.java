package com.example.login.controller;

import com.example.login.entity.RolEntity;
import com.example.login.services.RolServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/Rol")
@CrossOrigin
public class RolController {
    @Autowired
    private RolServices rolServices;
    @PostMapping("/save")
    public RolEntity saveRol(@RequestBody Map<String, String> requestBody) {
        String rolName = requestBody.get("rolName");
        RolEntity rolEntity = new RolEntity();
        rolEntity.setRolName(rolName);
        return rolServices.saveRol(rolEntity);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteRolById(@PathVariable int id) {
        rolServices.deleteRolById(id);
        return ResponseEntity.ok("Rol eliminado exitosamente");
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateRol(@PathVariable int id, @RequestBody RolEntity updatedRolEntity) {
        RolEntity updatedRol = rolServices.updateRol(id, updatedRolEntity);

        if (updatedRol.getIdRol() == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedRol);
        } else {
            return ResponseEntity.ok(updatedRol);
        }
    }
}
