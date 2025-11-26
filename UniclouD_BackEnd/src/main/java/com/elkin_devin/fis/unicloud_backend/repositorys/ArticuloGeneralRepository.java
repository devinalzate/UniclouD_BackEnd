package com.elkin_devin.fis.unicloud_backend.repositorys;

import com.elkin_devin.fis.unicloud_backend.entitys.ArticuloGeneralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloGeneralRepository extends JpaRepository<ArticuloGeneralEntity, ArticuloGeneralEntity.ArticuloGeneralId> {
    // Búsquedas por profesor
    List<ArticuloGeneralEntity> findByIdProfesor(Long profesorId);

    // Búsquedas por universidad
    List<ArticuloGeneralEntity> findByIdUniversidad(Long universidadId);

    // Búsquedas por asignatura
    List<ArticuloGeneralEntity> findByIdAsignatura(Long asignaturaId);

    // Búsquedas por material
    List<ArticuloGeneralEntity> findByIdMaterial(Long materialId);

    // Búsquedas combinadas
    List<ArticuloGeneralEntity> findByIdProfesorAndIdUniversidad(Long profesorId, Long universidadId);

    List<ArticuloGeneralEntity> findByIdProfesorAndIdUniversidadAndIdAsignatura(
            Long profesorId, Long universidadId, Long asignaturaId);

    List<ArticuloGeneralEntity> findByIdMaterialAndIdProfesor(Long materialId, Long profesorId);

    List<ArticuloGeneralEntity> findByIdMaterialAndIdUniversidad(Long materialId, Long universidadId);

    List<ArticuloGeneralEntity> findByIdMaterialAndIdAsignatura(Long materialId, Long asignaturaId);
}
