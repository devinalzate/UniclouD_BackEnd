package com.elkin_devin.fis.unicloud_backend.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "material")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long idMaterial;

    private String ruta_archivo;
    private String titulo;
    private String año; /* Este es guardado en forma \"2025-1\" */

    @OneToMany
    @JoinColumn(name = "material_id")  // Especifica la columna FK
    private List<ArticuloGeneralEntity> articulosRelacionados;
}
