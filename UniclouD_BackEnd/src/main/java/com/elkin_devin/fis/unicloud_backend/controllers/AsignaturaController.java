package com.elkin_devin.fis.unicloud_backend.controllers;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOAsignatura;
import com.elkin_devin.fis.unicloud_backend.services.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unicloud/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @PostMapping
    public ResponseEntity<?> crearAsignatura(@RequestBody DTOAsignatura dto) {
        if (asignaturaService.crearAsignatura(dto)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "success", true,
                            "message", "Asignatura creada",
                            "data", "creado"
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success", false,
                            "message", "e.getMessage()"
                    ));
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        List<DTOAsignatura> lista = asignaturaService.obtenerTodas();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        DTOAsignatura dto = asignaturaService.buscarPorId(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{materia}")
    public ResponseEntity<?> buscarPorMateria(@PathVariable String materia) {
        List<DTOAsignatura> lista = asignaturaService.buscarPorMateria(materia);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }
}
