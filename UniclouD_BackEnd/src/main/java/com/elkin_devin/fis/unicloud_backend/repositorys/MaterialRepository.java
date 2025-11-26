package com.elkin_devin.fis.unicloud_backend.repositorys;

import com.elkin_devin.fis.unicloud_backend.entitys.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {
    // Buscar materiales cuyo título contenga la cadena (case-insensitive)
    List<MaterialEntity> findByTituloContainingIgnoreCase(String titulo);

    // Buscar materiales por año exacto
    List<MaterialEntity> findByAño(String año);
}
