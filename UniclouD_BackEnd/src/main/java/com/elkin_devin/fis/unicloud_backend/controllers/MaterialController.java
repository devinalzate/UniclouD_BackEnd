package com.elkin_devin.fis.unicloud_backend.controllers;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOMaterial;
import com.elkin_devin.fis.unicloud_backend.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unicloud/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    /**
     * Crear material con archivo
     * Formato: multipart/form-data
     * - titulo (String)
     * - año (String)
     * - archivo (File)
     */
    @PostMapping
    public ResponseEntity<?> crearMaterial(
            @RequestParam String titulo,
            @RequestParam String año,
            @RequestParam MultipartFile archivo) {

        try {
            DTOMaterial dto = new DTOMaterial();
            dto.setTitulo(titulo);
            dto.setAño(año);

            if (materialService.crearMaterial(dto, archivo)) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of(
                                "success", true,
                                "message", "Material creado exitosamente",
                                "data", dto
                        ));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "success", false,
                                "message", "Error al crear el material"
                        ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", "Error interno: " + e.getMessage()
                    ));
        }
    }

    /**
     * Obtener todos los materiales
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<DTOMaterial> lista = materialService.obtenerTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar material por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        DTOMaterial dto = materialService.buscarPorId(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Buscar materiales por título
     */
    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<?> buscarPorTitulo(@PathVariable String titulo) {
        List<DTOMaterial> lista = materialService.buscarPorTitulo(titulo);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar materiales por año
     */
    @GetMapping("/buscar/ano/{año}")
    public ResponseEntity<?> buscarPorAño(@PathVariable String año) {
        List<DTOMaterial> lista = materialService.buscarPorAño(año);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Eliminar material por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMaterial(@PathVariable Long id) {
        if (materialService.eliminarMaterial(id)) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Material eliminado exitosamente"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "success", false,
                            "message", "Material no encontrado"
                    ));
        }
    }
}
