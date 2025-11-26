package com.elkin_devin.fis.unicloud_backend.controllers;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOProfesor;
import com.elkin_devin.fis.unicloud_backend.services.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unicloud/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    /**
     * Crear profesor
     */
    @PostMapping
    public ResponseEntity<?> crearProfesor(@RequestBody DTOProfesor dto) {
        try {
            if (profesorService.crearProfesor(dto)) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of(
                                "success", true,
                                "message", "Profesor creado exitosamente",
                                "data", dto
                        ));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "success", false,
                                "message", "Error al crear el profesor"
                        ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", "Error: " + e.getMessage()
                    ));
        }
    }

    /**
     * Obtener todos los profesores
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<DTOProfesor> lista = profesorService.obtenerTodas();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar profesor por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        DTOProfesor dto = profesorService.buscarPorId(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Buscar profesores por nombre
     */
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        List<DTOProfesor> lista = profesorService.buscarPorNombre(nombre);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }
}
