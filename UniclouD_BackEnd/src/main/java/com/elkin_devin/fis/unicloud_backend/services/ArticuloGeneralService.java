package com.elkin_devin.fis.unicloud_backend.services;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOArticuloGeneral;
import com.elkin_devin.fis.unicloud_backend.dtos.DTOMaterial;
import com.elkin_devin.fis.unicloud_backend.entitys.ArticuloGeneralEntity;
import com.elkin_devin.fis.unicloud_backend.entitys.MaterialEntity;
import com.elkin_devin.fis.unicloud_backend.utils.ArticuloGeneralMapper;
import com.elkin_devin.fis.unicloud_backend.repositorys.ArticuloGeneralRepository;
import com.elkin_devin.fis.unicloud_backend.repositorys.MaterialRepository;
import com.elkin_devin.fis.unicloud_backend.repositorys.UniversidadRepository;
import com.elkin_devin.fis.unicloud_backend.repositorys.AsignaturaRepository;
import com.elkin_devin.fis.unicloud_backend.repositorys.ProfesorRepository;
import com.elkin_devin.fis.unicloud_backend.utils.MaterialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticuloGeneralService {

    private final ArticuloGeneralRepository articuloGeneralRepository;
    private final MaterialRepository materialRepository;
    private final UniversidadRepository universidadRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final ProfesorRepository profesorRepository;
    private final ArticuloGeneralMapper articuloGeneralMapper;
    private final MaterialMapper materialMapper;

    /**
     * Crear artículo general validando que todas las entidades existan
     */
    public boolean crearArticuloGeneral(DTOArticuloGeneral dto) {
        try {
            // Validar que NO sean null
            if (dto.getMaterialId() == null) {
                throw new IllegalArgumentException("idMaterial no puede ser null");
            }
            if (dto.getUniversidadId() == null) {
                throw new IllegalArgumentException("idUniversidad no puede ser null");
            }
            if (dto.getAsignaturaId() == null) {
                throw new IllegalArgumentException("idAsignatura no puede ser null");
            }
            if (dto.getProfesorId() == null) {
                throw new IllegalArgumentException("idProfesor no puede ser null");
            }

            // Validar que exista Material
            if (!materialRepository.existsById(dto.getMaterialId())) {
                throw new IllegalArgumentException("Material con ID " + dto.getMaterialId() + " no existe");
            }

            // Validar que exista Universidad
            if (!universidadRepository.existsById(dto.getUniversidadId())) {
                throw new IllegalArgumentException("Universidad con ID " + dto.getUniversidadId() + " no existe");
            }

            // Validar que exista Asignatura
            if (!asignaturaRepository.existsById(dto.getAsignaturaId())) {
                throw new IllegalArgumentException("Asignatura con ID " + dto.getAsignaturaId() + " no existe");
            }

            // Validar que exista Profesor
            if (!profesorRepository.existsById(dto.getProfesorId())) {
                throw new IllegalArgumentException("Profesor con ID " + dto.getProfesorId() + " no existe");
            }

            // Convertir DTO a Entity
            ArticuloGeneralEntity entity = articuloGeneralMapper.dtoToEntity(dto);

            // Guardar en la base de datos
            articuloGeneralRepository.save(entity);

            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("Error al crear artículo general: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Buscar artículo general por clave compuesta
     */
    public DTOArticuloGeneral buscarPorClaveCompuesta(Long idMaterial, Long idUniversidad, Long idAsignatura, Long idProfesor) {
        try {
            ArticuloGeneralEntity.ArticuloGeneralId id =
                    new ArticuloGeneralEntity.ArticuloGeneralId(idMaterial, idUniversidad, idAsignatura, idProfesor);

            Optional<ArticuloGeneralEntity> entity = articuloGeneralRepository.findById(id);

            if (entity.isPresent()) {
                return articuloGeneralMapper.entityToDTO(entity.get());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Buscar artículos por Material
     */
    public List<DTOArticuloGeneral> buscarPorMaterial(Long idMaterial) {
        List<ArticuloGeneralEntity> entities = articuloGeneralRepository.findByIdMaterial(idMaterial);
        return articuloGeneralMapper.entitiesToDTOs(entities);
    }

    /**
     * Buscar artículos por Universidad
     */
    public List<DTOArticuloGeneral> buscarPorUniversidad(Long idUniversidad) {
        List<ArticuloGeneralEntity> entities = articuloGeneralRepository.findByIdUniversidad(idUniversidad);
        return articuloGeneralMapper.entitiesToDTOs(entities);
    }

    /**
     * Buscar artículos por Asignatura
     */
    public List<DTOArticuloGeneral> buscarPorAsignatura(Long idAsignatura) {
        List<ArticuloGeneralEntity> entities = articuloGeneralRepository.findByIdAsignatura(idAsignatura);
        return articuloGeneralMapper.entitiesToDTOs(entities);
    }

    /**
     * Buscar artículos por Profesor
     */
    public List<DTOArticuloGeneral> buscarPorProfesor(Long idProfesor) {
        List<ArticuloGeneralEntity> entities = articuloGeneralRepository.findByIdProfesor(idProfesor);
        return articuloGeneralMapper.entitiesToDTOs(entities);
    }

    /**
     * Buscar artículos por combinación (Profesor, Universidad, Asignatura)
     */
    public List<DTOArticuloGeneral> buscarPorProfesorYUniversidadYAsignatura(
            Long idProfesor, Long idUniversidad, Long idAsignatura) {
        List<ArticuloGeneralEntity> entities = articuloGeneralRepository
                .findByIdProfesorAndIdUniversidadAndIdAsignatura(idProfesor, idUniversidad, idAsignatura);
        return articuloGeneralMapper.entitiesToDTOs(entities);
    }

    /**
     * Obtener todos los artículos generales
     */
    public List<DTOArticuloGeneral> obtenerTodos() {
        List<ArticuloGeneralEntity> entities = articuloGeneralRepository.findAll();
        return articuloGeneralMapper.entitiesToDTOs(entities);
    }

    /**
     * Eliminar artículo general
     */
    public boolean eliminarArticuloGeneral(Long idMaterial, Long idUniversidad, Long idAsignatura, Long idProfesor) {
        try {
            ArticuloGeneralEntity.ArticuloGeneralId id =
                    new ArticuloGeneralEntity.ArticuloGeneralId(idMaterial, idUniversidad, idAsignatura, idProfesor);

            if (articuloGeneralRepository.existsById(id)) {
                articuloGeneralRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtener todos los artículos generales de una asignatura
     */
    public List<DTOArticuloGeneral> obtenerArticulosPorAsignatura(Long asignaturaId) {
        List<ArticuloGeneralEntity> entities = articuloGeneralRepository.findByIdAsignatura(asignaturaId);
        return articuloGeneralMapper.entitiesToDTOs(entities);
    }

    /**
     * Obtener materiales únicos de una asignatura (sin duplicados)
     */
    public List<DTOMaterial> obtenerMaterialesPorAsignatura(Long asignaturaId) {
        // Obtener todos los artículos de la asignatura
        List<ArticuloGeneralEntity> articulos = articuloGeneralRepository.findByIdAsignatura(asignaturaId);

        // Extraer IDs de materiales únicos
        List<Long> materialIds = articulos.stream()
                .map(ArticuloGeneralEntity::getIdMaterial)
                .distinct()
                .collect(Collectors.toList());

        // Obtener los materiales
        List<MaterialEntity> materiales = materialRepository.findAllById(materialIds);

        // Convertir a DTOs
        return materialMapper.entitiesToDTOs(materiales);
    }

    /**
     * Validar que una asignatura exista
     */
    public boolean existeAsignatura(Long asignaturaId) {
        return asignaturaRepository.existsById(asignaturaId);
    }

}
