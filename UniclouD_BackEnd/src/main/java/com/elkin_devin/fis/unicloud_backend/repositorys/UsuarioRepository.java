package com.elkin_devin.fis.unicloud_backend.repositorys;

import com.elkin_devin.fis.unicloud_backend.entitys.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
