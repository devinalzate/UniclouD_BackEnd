package com.elkin_devin.fis.unicloud_backend.controllers;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOProfesor;
import com.elkin_devin.fis.unicloud_backend.services.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RequiredArgsConstructor
@RestController("/unicloud/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    @PostMapping
    private ResponseEntity<?> crearProfesor(@RequestBody DTOProfesor dto) {
        if(profesorService.crearProfesor(dto)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    private ResponseEntity<?> getProfesores() {
        List<DTOProfesor> lista = profesorService.obtenerTodas();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(lista);
        }
    }
}
