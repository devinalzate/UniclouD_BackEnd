-- Tabla: Usuario
CREATE TABLE usuario (
     Id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     email VARCHAR(255) NOT NULL UNIQUE,
     contrasena VARCHAR(255) NOT NULL,
     usuario VARCHAR(255) NOT NULL UNIQUE
);

-- Tabla: Universidad
CREATE TABLE universidad (
     Id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     universidad VARCHAR(255) NOT NULL,
     ciudad VARCHAR(255) NOT NULL
);

-- Tabla: Profesor
CREATE TABLE profesores(
    Id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL
);

-- Tabla: Asignatura
CREATE TABLE asignatura (
    Id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    materia VARCHAR(255) NOT NULL,
    año VARCHAR(10) NOT NULL
);

-- Tabla: Material
CREATE TABLE material (
    Id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    titulo VARCHAR(255) NOT NULL,
    año VARCHAR(10) NOT NULL,
    ruta_archivo VARCHAR(500) NOT NULL DEFAULT ''
);

-- Tabla: ArticuloGeneral (tabla de relación)
CREATE TABLE articulo_general (
    material_id BIGINT NOT NULL,
    IdUniversidad BIGINT NOT NULL,
    IdAsignatura BIGINT NOT NULL,
    IdProfesor BIGINT NOT NULL,

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
CREATE INDEX idx_material_ruta_archivo ON material(ruta_archivo);
