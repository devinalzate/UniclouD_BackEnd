package com.elkin_devin.fis.unicloud_backend.services;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOUniversidad;
import com.elkin_devin.fis.unicloud_backend.entitys.UniversidadEntity;
import com.elkin_devin.fis.unicloud_backend.repositorys.UniversidadRepository;
import com.elkin_devin.fis.unicloud_backend.utils.UniversidadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversidadService {
    private final UniversidadRepository universidadRepository;
    private final UniversidadMapper mapper;

    /**
     * Crear una nueva universidad
     * @param dto DTO de Universidad a crear
     * @return true si se creó exitosamente, false en caso contrario
     */
    public boolean crearUniversidad(DTOUniversidad dto) {
        try {
            // Convertir DTO a Entity
            UniversidadEntity entity = mapper.dtoToEntity(dto);

            // Guardar en la base de datos
            universidadRepository.save(entity);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Buscar universidad por ID
     * @param id ID de la universidad
     * @return UniversidadDTO si existe, null en caso contrario
     */
    public DTOUniversidad buscarPorId(Long id) {
        Optional<UniversidadEntity> entity = universidadRepository.findById(id);

        if (entity.isPresent()) {
            return mapper.entityToDTO(entity.get());
        }
        return null;
    }

    /**
     * Buscar universidades por nombre
     * @param nombre Nombre de la universidad
     * @return Lista de UniversidadDTO que coinciden con el nombre
     */
    public List<DTOUniversidad> buscarPorNombre(String nombre) {
        List<UniversidadEntity> entities = universidadRepository.findByUniversidadContainingIgnoreCase(nombre);

        return mapper.entitiesToDTOs(entities);
    }

    /**
     * Obtener todas las universidades
     * @return Lista de todos los UniversidadDTO
     */
    public List<DTOUniversidad> obtenerTodas() {
        List<UniversidadEntity> entities = universidadRepository.findAll();

        return mapper.entitiesToDTOs(entities);
    }
}
