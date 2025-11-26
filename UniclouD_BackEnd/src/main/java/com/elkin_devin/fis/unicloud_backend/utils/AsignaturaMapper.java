package com.elkin_devin.fis.unicloud_backend.utils;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOAsignatura;
import com.elkin_devin.fis.unicloud_backend.entitys.AsignaturaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsignaturaMapper {
    // ============ CONVERSIÓN INDIVIDUAL ============

    // Entity → DTO
    public DTOAsignatura entityToDTO(AsignaturaEntity entity) {
        if (entity == null) {
            return null;
        }

        DTOAsignatura dto = new DTOAsignatura();
        dto.setIdAsignatura(entity.getId());
        dto.setMateria(entity.getMateria());
        dto.setAño(entity.getAño());

        return dto;
    }

    // DTO → Entity
    public AsignaturaEntity dtoToEntity(DTOAsignatura dto) {
        if (dto == null) {
            return null;
        }

        AsignaturaEntity entity = new AsignaturaEntity();
        entity.setId(null); // Ignorar ID del DTO (será generado por BD)
        entity.setMateria(dto.getMateria());
        entity.setAño(dto.getAño());

        return entity;
    }

    // ============ CONVERSIÓN DE LISTAS ============

    // List<Entity> → List<DTO>
    public List<DTOAsignatura> entitiesToDTOs(List<AsignaturaEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    // List<DTO> → List<Entity>
    public List<AsignaturaEntity> dtosToEntities(List<DTOAsignatura> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
