package com.elkin_devin.fis.unicloud_backend.controllers;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOUniversidad;
import com.elkin_devin.fis.unicloud_backend.services.UniversidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unicloud/universidades")
@RequiredArgsConstructor
public class UniversidadController {
    private final UniversidadService universidadService;

    @PostMapping
    private ResponseEntity<?> crearUniversidad(@RequestBody DTOUniversidad universidad) {
        if(universidadService.crearUniversidad(universidad)){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "success", true,
                            "message", "Asignatura creada",
                            "data", "created"
                    ));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success", false,
                            "message", "e.getMessage()"
                    ));
        }
    }

    @GetMapping
    private List<DTOUniversidad> listaUniversidades() {
        List<DTOUniversidad> lista = universidadService.obtenerTodas();
        return lista;
    }
}
