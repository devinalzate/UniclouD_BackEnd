package com.elkin_devin.fis.unicloud_backend.repositorys;

import com.elkin_devin.fis.unicloud_backend.entitys.UniversidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversidadRepository extends JpaRepository<UniversidadEntity, Long> {
    // Buscar universidades cuyo nombre contenga la cadena (case-insensitive)
    List<UniversidadEntity> findByUniversidadContainingIgnoreCase(String nombre);
}
