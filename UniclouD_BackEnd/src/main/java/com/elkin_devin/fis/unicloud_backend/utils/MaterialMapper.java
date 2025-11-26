package com.elkin_devin.fis.unicloud_backend.utils;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOMaterial;
import com.elkin_devin.fis.unicloud_backend.entitys.MaterialEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaterialMapper {

    // ============ CONVERSIÓN INDIVIDUAL ============

    // Entity → DTO
    public DTOMaterial entityToDTO(MaterialEntity entity) {
        if (entity == null) {
            return null;
        }

        DTOMaterial dto = new DTOMaterial();
        dto.setIdMaterial(entity.getIdMaterial());
        dto.setRuta_archivo(entity.getRuta_archivo());
        dto.setTitulo(entity.getTitulo());
        dto.setAño(entity.getAño());

        return dto;
    }

    // DTO → Entity
    public MaterialEntity dtoToEntity(DTOMaterial dto) {
        if (dto == null) {
            return null;
        }

        MaterialEntity entity = new MaterialEntity();
        entity.setIdMaterial(null); // Ignorar ID del DTO (será generado por BD)
        entity.setRuta_archivo(dto.getRuta_archivo()); // Se asigna después de guardar el archivo
        entity.setTitulo(dto.getTitulo());
        entity.setAño(dto.getAño());

        return entity;
    }

    // ============ CONVERSIÓN DE LISTAS ============

    // List<Entity> → List<DTO>
    public List<DTOMaterial> entitiesToDTOs(List<MaterialEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    // List<DTO> → List<Entity>
    public List<MaterialEntity> dtosToEntities(List<DTOMaterial> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
