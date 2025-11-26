package com.elkin_devin.fis.unicloud_backend.repositorys;

import com.elkin_devin.fis.unicloud_backend.entitys.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Long> {
    List<AsignaturaEntity> findByMateriaContainingIgnoreCase(String nombre);
}
