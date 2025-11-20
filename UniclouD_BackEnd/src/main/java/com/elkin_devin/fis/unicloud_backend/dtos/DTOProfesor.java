package com.elkin_devin.fis.unicloud_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DTOProfesor {
    private Long idProfesor;
    private String nombre;
    private String apellido;
}
