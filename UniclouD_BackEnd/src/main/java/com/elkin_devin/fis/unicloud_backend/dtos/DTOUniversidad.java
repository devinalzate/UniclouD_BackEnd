package com.elkin_devin.fis.unicloud_backend.dtos;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DTOUniversidad {
    private Long idUniversidad;
    private String universidad;
    private String ciudad;
}
