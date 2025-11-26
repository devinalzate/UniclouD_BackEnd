package com.elkin_devin.fis.unicloud_backend.repositorys;

import com.elkin_devin.fis.unicloud_backend.entitys.ProfesorEntity;
import com.elkin_devin.fis.unicloud_backend.entitys.UniversidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Long> {
    // Buscar profesores cuyo nombre contenga la cadena (case-insensitive)
    List<ProfesorEntity> findByNombreContainingIgnoreCase(String nombre);
}
