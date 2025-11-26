package com.elkin_devin.fis.unicloud_backend.controllers;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOArticuloGeneral;
import com.elkin_devin.fis.unicloud_backend.dtos.DTOMaterial;
import com.elkin_devin.fis.unicloud_backend.services.ArticuloGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unicloud/articulos-generales")
@RequiredArgsConstructor
public class ArticuloGeneralController {

    private final ArticuloGeneralService articuloGeneralService;

    /**
     * Crear artículo general
     * POST /unicloud/articulos-generales
     */
    @PostMapping
    public ResponseEntity<?> crearArticuloGeneral(@RequestBody DTOArticuloGeneral dto) {
        try {
            if (articuloGeneralService.crearArticuloGeneral(dto)) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of(
                                "success", true,
                                "message", "Artículo general creado exitosamente",
                                "data", dto
                        ));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "success", false,
                                "message", "Error al crear el artículo general"
                        ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success", false,
                            "message", "Error: " + e.getMessage()
                    ));
        }
    }

    /**
     * Obtener todos los artículos generales
     * GET /unicloud/articulos-generales
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<DTOArticuloGeneral> lista = articuloGeneralService.obtenerTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar por clave compuesta
     * GET /unicloud/articulos-generales/buscar/compuesta?material=1&universidad=1&asignatura=1&profesor=1
     */
    @GetMapping("/buscar/compuesta")
    public ResponseEntity<?> buscarPorClaveCompuesta(
            @RequestParam Long material,
            @RequestParam Long universidad,
            @RequestParam Long asignatura,
            @RequestParam Long profesor) {

        DTOArticuloGeneral dto = articuloGeneralService.buscarPorClaveCompuesta(material, universidad, asignatura, profesor);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Buscar por Material
     * GET /unicloud/articulos-generales/material/1
     */
    @GetMapping("/material/{idMaterial}")
    public ResponseEntity<?> buscarPorMaterial(@PathVariable Long idMaterial) {
        List<DTOArticuloGeneral> lista = articuloGeneralService.buscarPorMaterial(idMaterial);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar por Universidad
     * GET /unicloud/articulos-generales/universidad/1
     */
    @GetMapping("/universidad/{idUniversidad}")
    public ResponseEntity<?> buscarPorUniversidad(@PathVariable Long idUniversidad) {
        List<DTOArticuloGeneral> lista = articuloGeneralService.buscarPorUniversidad(idUniversidad);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar por Asignatura
     * GET /unicloud/articulos-generales/asignatura/1
     */
    @GetMapping("/asignatura/{idAsignatura}")
    public ResponseEntity<?> buscarPorAsignatura(@PathVariable Long idAsignatura) {
        List<DTOArticuloGeneral> lista = articuloGeneralService.buscarPorAsignatura(idAsignatura);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar por Profesor
     * GET /unicloud/articulos-generales/profesor/1
     */
    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<?> buscarPorProfesor(@PathVariable Long idProfesor) {
        List<DTOArticuloGeneral> lista = articuloGeneralService.buscarPorProfesor(idProfesor);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Buscar por Profesor, Universidad y Asignatura
     * GET /unicloud/articulos-generales/buscar/filtro?profesor=1&universidad=1&asignatura=1
     */
    @GetMapping("/buscar/filtro")
    public ResponseEntity<?> buscarPorFiltro(
            @RequestParam Long profesor,
            @RequestParam Long universidad,
            @RequestParam Long asignatura) {

        List<DTOArticuloGeneral> lista = articuloGeneralService
                .buscarPorProfesorYUniversidadYAsignatura(profesor, universidad, asignatura);

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    /**
     * Eliminar artículo general
     * DELETE /unicloud/articulos-generales?material=1&universidad=1&asignatura=1&profesor=1
     */
    @DeleteMapping
    public ResponseEntity<?> eliminarArticuloGeneral(
            @RequestParam Long material,
            @RequestParam Long universidad,
            @RequestParam Long asignatura,
            @RequestParam Long profesor) {

        if (articuloGeneralService.eliminarArticuloGeneral(material, universidad, asignatura, profesor)) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Artículo general eliminado exitosamente"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "success", false,
                            "message", "Artículo general no encontrado"
                    ));
        }
    }

    /**
     * Obtener todos los artículos generales de una asignatura
     * GET /unicloud/articulos-generales/asignatura/{asignaturaId}
     */
    @GetMapping("/asignatura/{asignaturaId}")
    public ResponseEntity<?> obtenerArticulosPorAsignatura(@PathVariable Long asignaturaId) {
        try {
            // Validar que la asignatura exista (usando el service)
            if (!articuloGeneralService.existeAsignatura(asignaturaId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "success", false,
                                "message", "Asignatura con ID " + asignaturaId + " no existe"
                        ));
            }

            List<DTOArticuloGeneral> articulos = articuloGeneralService.obtenerArticulosPorAsignatura(asignaturaId);

            if (articulos.isEmpty()) {
                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "No hay artículos generales para esta asignatura",
                        "data", articulos
                ));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Artículos encontrados",
                    "cantidad", articulos.size(),
                    "data", articulos
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", "Error: " + e.getMessage()
                    ));
        }
    }

    /**
     * Obtener todos los materiales relacionados a una asignatura
     * GET /unicloud/articulos-generales/asignatura/{asignaturaId}/materiales
     */
    @GetMapping("/asignatura/{asignaturaId}/materiales")
    public ResponseEntity<?> obtenerMaterialesPorAsignatura(@PathVariable Long asignaturaId) {
        try {
            // Validar que la asignatura exista (usando el service)
            if (!articuloGeneralService.existeAsignatura(asignaturaId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "success", false,
                                "message", "Asignatura con ID " + asignaturaId + " no existe"
                        ));
            }

            List<DTOMaterial> materiales = articuloGeneralService.obtenerMaterialesPorAsignatura(asignaturaId);

            if (materiales.isEmpty()) {
                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "No hay materiales para esta asignatura",
                        "data", materiales
                ));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Materiales encontrados",
                    "cantidad", materiales.size(),
                    "data", materiales
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", "Error: " + e.getMessage()
                    ));
        }
    }
}
