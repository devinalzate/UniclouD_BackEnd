package com.elkin_devin.fis.unicloud_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DTOArticuloGeneral {
    private Long materialId;
    private Long universidadId;
    private Long asignaturaId;
    private Long profesorId;
}
