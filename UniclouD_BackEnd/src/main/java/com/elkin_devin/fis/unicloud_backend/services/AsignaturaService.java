package com.elkin_devin.fis.unicloud_backend.services;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOAsignatura;
import com.elkin_devin.fis.unicloud_backend.entitys.AsignaturaEntity;
import com.elkin_devin.fis.unicloud_backend.repositorys.AsignaturaRepository;
import com.elkin_devin.fis.unicloud_backend.utils.AsignaturaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final AsignaturaMapper asignaturaMapper;

    public boolean crearAsignatura(DTOAsignatura dto) {
        try {
            // Convertir DTO a Entity
            AsignaturaEntity entity = asignaturaMapper.dtoToEntity(dto);

            // Guardar en la base de datos
            asignaturaRepository.save(entity);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DTOAsignatura buscarPorId(Long id) {
        Optional<AsignaturaEntity> entity = asignaturaRepository.findById(id);

        if (entity.isPresent()) {
            return asignaturaMapper.entityToDTO(entity.get());
        }
        return null;
    }

    public List<DTOAsignatura> buscarPorMateria(String materia) {
        List<AsignaturaEntity> entities = asignaturaRepository.findByMateriaContainingIgnoreCase(materia);

        return asignaturaMapper.entitiesToDTOs(entities);
    }

    public List<DTOAsignatura> obtenerTodas() {
        List<AsignaturaEntity> entities = asignaturaRepository.findAll();

        return asignaturaMapper.entitiesToDTOs(entities);
    }
}
