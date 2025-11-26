package com.elkin_devin.fis.unicloud_backend.utils;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOUniversidad;
import com.elkin_devin.fis.unicloud_backend.entitys.UniversidadEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UniversidadMapper {
    // Entity → DTO
    public DTOUniversidad entityToDTO(UniversidadEntity entity) {
        if (entity == null) {
            return null;
        }

        DTOUniversidad dto = new DTOUniversidad();
        dto.setIdUniversidad(entity.getIdUniversidad());
        dto.setUniversidad(entity.getUniversidad());
        dto.setCiudad(entity.getCiudad());

        return dto;
    }

    // DTO → Entity
    public UniversidadEntity dtoToEntity(DTOUniversidad dto) {
        if (dto == null) return null;
        UniversidadEntity entity = new UniversidadEntity();
        entity.setIdUniversidad(null); // Ignora el id del DTO y deja que la BD lo asigne
        entity.setUniversidad(dto.getUniversidad());
        entity.setCiudad(dto.getCiudad());
        return entity;
    }

    // List<Entity> → List<DTO>
    public List<DTOUniversidad> entitiesToDTOs(List<UniversidadEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    // List<DTO> → List<Entity>
    public List<UniversidadEntity> dtosToEntities(List<DTOUniversidad> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
