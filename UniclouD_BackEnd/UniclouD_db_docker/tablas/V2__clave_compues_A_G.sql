-- V2__Actualizar_articulo_general_con_clave_compuesta.sql

-- Eliminar la tabla existente (solo si es necesario y no tienes datos)
DROP TABLE IF EXISTS articulo_general CASCADE;

-- Crear la nueva tabla con clave compuesta
CREATE TABLE articulo_general (
    material_id INTEGER NOT NULL,
    IdUniversidad INTEGER NOT NULL,
    IdAsignatura INTEGER NOT NULL,
    IdProfesor INTEGER NOT NULL,

    PRIMARY KEY (material_id, IdUniversidad, IdAsignatura, IdProfesor),

    CONSTRAINT fk_material FOREIGN KEY (material_id) REFERENCES material(Id) ON DELETE CASCADE,
    CONSTRAINT fk_universidad FOREIGN KEY (IdUniversidad) REFERENCES universidad(Id) ON DELETE RESTRICT,
    CONSTRAINT fk_asignatura FOREIGN KEY (IdAsignatura) REFERENCES asignatura(Id) ON DELETE RESTRICT,
    CONSTRAINT fk_profesor FOREIGN KEY (IdProfesor) REFERENCES profesores(Id) ON DELETE RESTRICT
);

-- Crear índices para optimizar búsquedas
CREATE INDEX idx_articulo_material ON articulo_general(material_id);
CREATE INDEX idx_articulo_universidad ON articulo_general(IdUniversidad);
CREATE INDEX idx_articulo_asignatura ON articulo_general(IdAsignatura);
CREATE INDEX idx_articulo_profesor ON articulo_general(IdProfesor);