package com.elkin_devin.fis.unicloud_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DTOUsuario {
    private Long idUsuario;
    private String email;
    private String contrasena;
    private String usuario;
}
