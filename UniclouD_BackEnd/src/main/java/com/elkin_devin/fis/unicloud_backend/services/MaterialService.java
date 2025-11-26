package com.elkin_devin.fis.unicloud_backend.services;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOArticuloGeneral;
import com.elkin_devin.fis.unicloud_backend.dtos.DTOMaterial;
import com.elkin_devin.fis.unicloud_backend.entitys.ArticuloGeneralEntity;
import com.elkin_devin.fis.unicloud_backend.entitys.MaterialEntity;
import com.elkin_devin.fis.unicloud_backend.repositorys.ArticuloGeneralRepository;
import com.elkin_devin.fis.unicloud_backend.utils.ArticuloGeneralMapper;
import com.elkin_devin.fis.unicloud_backend.utils.MaterialMapper;
import com.elkin_devin.fis.unicloud_backend.repositorys.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final ArticuloGeneralRepository articuloGeneralRepository;
    private final ArticuloGeneralMapper articuloGeneralMapper;

    // Ruta base para guardar archivos (dentro de resources)
    private static final String UPLOAD_DIR = "src/main/resources/uploads/";


    /**
     * Crear material con archivo y asociarlo a múltiples entidades
     * Crea un ArticuloGeneral para cada combinación de universidad, asignatura y profesor
     */
    public boolean crearMaterialConArticulos(DTOMaterial materialDTO, MultipartFile archivo,
                                             List<Long> universidadIds,
                                             List<Long> asignaturaIds,
                                             List<Long> profesorIds) {
        try {
            // Validar archivo
            if (archivo == null || archivo.isEmpty()) {
                throw new IllegalArgumentException("El archivo no puede estar vacío");
            }

            // Validar que al menos haya una de cada
            if (universidadIds == null || universidadIds.isEmpty()) {
                throw new IllegalArgumentException("Debe proporcionar al menos una universidad");
            }
            if (asignaturaIds == null || asignaturaIds.isEmpty()) {
                throw new IllegalArgumentException("Debe proporcionar al menos una asignatura");
            }
            if (profesorIds == null || profesorIds.isEmpty()) {
                throw new IllegalArgumentException("Debe proporcionar al menos un profesor");
            }

            // Crear el material con el archivo
            boolean materialCreado = crearMaterial(materialDTO, archivo);
            if (!materialCreado) {
                throw new Exception("Error al crear el material");
            }

            // Obtener el ID del material creado
            List<MaterialEntity> materiales = materialRepository.findByTituloContainingIgnoreCase(materialDTO.getTitulo());
            if (materiales.isEmpty()) {
                throw new Exception("No se pudo recuperar el material creado");
            }
            Long materialId = materiales.get(0).getIdMaterial();

            // Crear ArticuloGeneral para cada combinación
            for (Long universidadId : universidadIds) {
                for (Long asignaturaId : asignaturaIds) {
                    for (Long profesorId : profesorIds) {
                        DTOArticuloGeneral articuloDTO = new DTOArticuloGeneral();
                        articuloDTO.setMaterialId(materialId);
                        articuloDTO.setUniversidadId(universidadId);
                        articuloDTO.setAsignaturaId(asignaturaId);
                        articuloDTO.setProfesorId(profesorId);

                        // Crear el artículo (sin validar existencia de entidades, asumimos que ya existen)
                        try {
                            ArticuloGeneralEntity entity = articuloGeneralMapper.dtoToEntity(articuloDTO);
                            articuloGeneralRepository.save(entity);
                        } catch (Exception e) {
                            System.err.println("Error al crear artículo: " + e.getMessage());
                        }
                    }
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Descargar material por ID
     * Retorna el archivo como bytes
     */
    public byte[] descargarMaterial(Long id) throws IOException {
        Optional<MaterialEntity> material = materialRepository.findById(id);

        if (material.isEmpty()) {
            throw new IllegalArgumentException("Material con ID " + id + " no existe");
        }

        String rutaArchivo = material.get().getRuta_archivo();

        if (rutaArchivo == null || rutaArchivo.isEmpty()) {
            throw new IllegalArgumentException("El material no tiene archivo asociado");
        }

        Path path = Paths.get(rutaArchivo);

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("El archivo no existe en la ruta: " + rutaArchivo);
        }

        return Files.readAllBytes(path);
    }

    /**
     * Obtener el nombre del archivo
     */
    public String obtenerNombreArchivo(Long id) throws IOException {
        Optional<MaterialEntity> material = materialRepository.findById(id);

        if (material.isEmpty()) {
            throw new IllegalArgumentException("Material con ID " + id + " no existe");
        }

        String rutaArchivo = material.get().getRuta_archivo();
        Path path = Paths.get(rutaArchivo);

        return path.getFileName().toString();
    }

    /**
     * Crear material con archivo
     */
    public boolean crearMaterial(DTOMaterial dto, MultipartFile archivo) {
        try {
            // Validar que el archivo no sea vacío
            if (archivo == null || archivo.isEmpty()) {
                return false;
            }

            // Generar nombre único para el directorio
            String nombreUnico = UUID.randomUUID().toString();
            String nombreArchivo = archivo.getOriginalFilename();
            String extensionArchivo = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));

            // Crear directorio único
            Path directorioMaterial = Paths.get(UPLOAD_DIR + nombreUnico);
            Files.createDirectories(directorioMaterial);

            // Guardar archivo en el directorio
            Path rutaArchivo = directorioMaterial.resolve(nombreUnico + extensionArchivo);
            Files.write(rutaArchivo, archivo.getBytes());

            // Asignar ruta al DTO
            dto.setRuta_archivo(rutaArchivo.toString());

            // Convertir DTO a Entity
            MaterialEntity entity = materialMapper.dtoToEntity(dto);

            // Guardar en la base de datos
            materialRepository.save(entity);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Buscar material por ID
     */
    public DTOMaterial buscarPorId(Long id) {
        Optional<MaterialEntity> entity = materialRepository.findById(id);

        if (entity.isPresent()) {
            return materialMapper.entityToDTO(entity.get());
        }
        return null;
    }

    /**
     * Buscar material por título
     */
    public List<DTOMaterial> buscarPorTitulo(String titulo) {
        List<MaterialEntity> entities = materialRepository.findByTituloContainingIgnoreCase(titulo);

        return materialMapper.entitiesToDTOs(entities);
    }

    /**
     * Buscar material por año
     */
    public List<DTOMaterial> buscarPorAño(String año) {
        List<MaterialEntity> entities = materialRepository.findByAño(año);

        return materialMapper.entitiesToDTOs(entities);
    }

    /**
     * Obtener todos los materiales
     */
    public List<DTOMaterial> obtenerTodos() {
        List<MaterialEntity> entities = materialRepository.findAll();

        return materialMapper.entitiesToDTOs(entities);
    }

    /**
     * Eliminar material y su archivo
     */
    public boolean eliminarMaterial(Long id) {
        try {
            Optional<MaterialEntity> entity = materialRepository.findById(id);

            if (entity.isPresent()) {
                // Eliminar archivo del sistema de archivos
                String rutaArchivo = entity.get().getRuta_archivo();
                if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
                    Path path = Paths.get(rutaArchivo);
                    if (Files.exists(path)) {
                        // Eliminar el archivo
                        Files.delete(path);

                        // Eliminar el directorio padre si está vacío
                        Path directorioPadre = path.getParent();
                        if (directorioPadre != null && Files.exists(directorioPadre)) {
                            Files.delete(directorioPadre);
                        }
                    }
                }

                // Eliminar del repositorio
                materialRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
