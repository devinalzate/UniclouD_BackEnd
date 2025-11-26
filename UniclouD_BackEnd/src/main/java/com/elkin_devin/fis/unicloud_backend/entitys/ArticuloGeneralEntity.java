package com.elkin_devin.fis.unicloud_backend.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "articulo_general")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@IdClass(ArticuloGeneralEntity.ArticuloGeneralId.class)
public class ArticuloGeneralEntity {

    @Id
    @Column(name = "material_id")
    private Long idMaterial;

    @Id
    @Column(name = "iduniversidad")
    private Long idUniversidad;

    @Id
    @Column(name = "idasignatura")
    private Long idAsignatura;

    @Id
    @Column(name = "idprofesor")
    private Long idProfesor;
//
//    // Relación ManyToOne hacia Material
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "material_id", insertable = false, updatable = false)
//    private MaterialEntity material;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "iduniversidad", insertable = false, updatable = false)
//    private UniversidadEntity universidad;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idasignatura", insertable = false, updatable = false)
//    private AsignaturaEntity asignatura;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idprofesor", insertable = false, updatable = false)
//    private ProfesorEntity profesor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArticuloGeneralId implements Serializable {
        private Long idMaterial;
        private Long idUniversidad;
        private Long idAsignatura;
        private Long idProfesor;
    }
}
