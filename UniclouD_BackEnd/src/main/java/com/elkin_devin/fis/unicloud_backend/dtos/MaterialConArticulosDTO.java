package com.elkin_devin.fis.unicloud_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialConArticulosDTO {
    private String titulo;
    private String año;
    private List<Long> universidadIds;
    private List<Long> asignaturaIds;
    private List<Long> profesorIds;
}
