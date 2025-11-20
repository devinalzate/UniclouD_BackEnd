-- V3__Agregar_ruta_archivo_a_material.sql

ALTER TABLE material
ADD COLUMN ruta_archivo VARCHAR(500) NOT NULL DEFAULT '';

-- Crear índice para optimizar búsquedas de archivos
CREATE INDEX idx_material_ruta_archivo ON material(ruta_archivo);
