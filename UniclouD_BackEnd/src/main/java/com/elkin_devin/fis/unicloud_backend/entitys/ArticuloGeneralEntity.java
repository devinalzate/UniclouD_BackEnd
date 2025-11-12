package com.elkin_devin.fis.unicloud_backend.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "articulo_general")
public class ArticuloGeneralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Clave foránea hacia Material
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private MaterialEntity material;

    // Agrega aquí las relaciones con Universidad, Asignatura, Profesor
    @Column(name = "IdUniversidad")
    private Long idUniversidad;

    @Column(name = "IdAsignatura")
    private Long idAsignatura;

    @Column(name = "IdProfesor")
    private Long idProfesor;
}
