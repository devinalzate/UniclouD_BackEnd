package com.elkin_devin.fis.unicloud_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DTOArticuloGeneral {
    private Long id;
    private DTOMaterial materialId;
    private DTOUniversidad universidadId;
    private DTOAsignatura asignaturaId;
    private DTOProfesor profesorId;
}
