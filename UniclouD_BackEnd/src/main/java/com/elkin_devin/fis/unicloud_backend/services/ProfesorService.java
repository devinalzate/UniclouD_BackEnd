package com.elkin_devin.fis.unicloud_backend.services;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOProfesor;
import com.elkin_devin.fis.unicloud_backend.entitys.ProfesorEntity;
import com.elkin_devin.fis.unicloud_backend.repositorys.ProfesorRepository;
import com.elkin_devin.fis.unicloud_backend.utils.ProfesorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorService {
    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;

    public boolean crearProfesor(DTOProfesor dto) {
        try {
            // Convertir DTO a Entity
            ProfesorEntity entity = profesorMapper.dtoToEntity(dto);

            // Guardar en la base de datos
            profesorRepository.save(entity);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DTOProfesor buscarPorId(Long id) {
        Optional<ProfesorEntity> entity = profesorRepository.findById(id);

        if (entity.isPresent()) {
            return profesorMapper.entityToDTO(entity.get());
        }
        return null;
    }

    public List<DTOProfesor> buscarPorNombre(String nombre) {
        List<ProfesorEntity> entities = profesorRepository.findByNombreContainingIgnoreCase(nombre);

        return profesorMapper.entitiesToDTOs(entities);
    }
    public List<DTOProfesor> obtenerTodas() {
        List<ProfesorEntity> entities = profesorRepository.findAll();

        return profesorMapper.entitiesToDTOs(entities);
    }
}
