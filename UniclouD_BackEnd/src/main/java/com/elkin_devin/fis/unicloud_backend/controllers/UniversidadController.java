package com.elkin_devin.fis.unicloud_backend.controllers;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOUniversidad;
import com.elkin_devin.fis.unicloud_backend.services.UniversidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unicloud/universidades")
@RequiredArgsConstructor
public class UniversidadController {
    private final UniversidadService universidadService;

    @PostMapping
    private ResponseEntity<?> crearUniversidad(@RequestBody DTOUniversidad universidad) {
        if(universidadService.crearUniversidad(universidad)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    private List<DTOUniversidad> listaUniversidades() {
        List<DTOUniversidad> lista = universidadService.obtenerTodas();
        return lista;
    }
}
