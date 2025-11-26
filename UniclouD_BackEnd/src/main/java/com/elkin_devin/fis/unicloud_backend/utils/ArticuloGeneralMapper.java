package com.elkin_devin.fis.unicloud_backend.utils;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOArticuloGeneral;
import com.elkin_devin.fis.unicloud_backend.entitys.ArticuloGeneralEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticuloGeneralMapper {

    // ============ CONVERSIÓN INDIVIDUAL ============

    // Entity → DTO
    public DTOArticuloGeneral entityToDTO(ArticuloGeneralEntity entity) {
        if (entity == null) {
            return null;
        }

        DTOArticuloGeneral dto = new DTOArticuloGeneral();
        dto.setMaterialId(entity.getIdMaterial());
        dto.setUniversidadId(entity.getIdUniversidad());
        dto.setAsignaturaId(entity.getIdAsignatura());
        dto.setProfesorId(entity.getIdProfesor());

        return dto;
    }

    // DTO → Entity
    public ArticuloGeneralEntity dtoToEntity(DTOArticuloGeneral dto) {
        if (dto == null) {
            return null;
        }

        ArticuloGeneralEntity entity = new ArticuloGeneralEntity();
        entity.setIdMaterial(dto.getMaterialId());
        entity.setIdUniversidad(dto.getUniversidadId());
        entity.setIdAsignatura(dto.getAsignaturaId());
        entity.setIdProfesor(dto.getProfesorId());

        return entity;
    }

    // ============ CONVERSIÓN DE LISTAS ============

    // List<Entity> → List<DTO>
    public List<DTOArticuloGeneral> entitiesToDTOs(List<ArticuloGeneralEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    // List<DTO> → List<Entity>
    public List<ArticuloGeneralEntity> dtosToEntities(List<DTOArticuloGeneral> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
