package com.elkin_devin.fis.unicloud_backend.utils;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOProfesor;
import com.elkin_devin.fis.unicloud_backend.entitys.ProfesorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfesorMapper {

    // ============ CONVERSIÓN INDIVIDUAL ============

    // Entity → DTO
    public DTOProfesor entityToDTO(ProfesorEntity entity) {
        if (entity == null) {
            return null;
        }

        DTOProfesor dto = new DTOProfesor();
        dto.setIdProfesor(entity.getIdProfesor());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());

        return dto;
    }

    // DTO → Entity
    public ProfesorEntity dtoToEntity(DTOProfesor dto) {
        if (dto == null) {
            return null;
        }

        ProfesorEntity entity = new ProfesorEntity();
        entity.setIdProfesor(null); // Ignorar ID del DTO (será generado por BD)
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());

        return entity;
    }

    // ============ CONVERSIÓN DE LISTAS ============

    // List<Entity> → List<DTO>
    public List<DTOProfesor> entitiesToDTOs(List<ProfesorEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    // List<DTO> → List<Entity>
    public List<ProfesorEntity> dtosToEntities(List<DTOProfesor> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
