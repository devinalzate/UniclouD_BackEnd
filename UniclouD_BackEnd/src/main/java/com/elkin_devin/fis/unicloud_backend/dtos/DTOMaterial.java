package com.elkin_devin.fis.unicloud_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DTOMaterial {
    private Long idMaterial;
    private String titulo;
    private String año;
}
