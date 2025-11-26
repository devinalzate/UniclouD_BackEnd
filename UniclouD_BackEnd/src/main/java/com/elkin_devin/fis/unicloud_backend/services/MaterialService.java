package com.elkin_devin.fis.unicloud_backend.services;

import com.elkin_devin.fis.unicloud_backend.dtos.DTOMaterial;
import com.elkin_devin.fis.unicloud_backend.entitys.MaterialEntity;
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

    // Ruta base para guardar archivos (dentro de resources)
    private static final String UPLOAD_DIR = "src/main/resources/uploads/";

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
