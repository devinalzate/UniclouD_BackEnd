-- Tabla: Usuario
CREATE TABLE usuario (
     Id SERIAL PRIMARY KEY,
     email VARCHAR(255) NOT NULL UNIQUE,
     contrasena VARCHAR(255) NOT NULL,
     usuario VARCHAR(255) NOT NULL UNIQUE
);

-- Tabla: Universidad
CREATE TABLE universidad (
     Id SERIAL PRIMARY KEY,
     universidad VARCHAR(255) NOT NULL,
     ciudad VARCHAR(255) NOT NULL
);

-- Tabla: Profesor
CREATE TABLE profesores (  -- Nota: "profesores" coincide con @Table(name = "profesores")
    Id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL
);

-- Tabla: Asignatura
CREATE TABLE asignatura (
    Id SERIAL PRIMARY KEY,
    materia VARCHAR(255) NOT NULL,
    año VARCHAR(10) NOT NULL
);

-- Tabla: Material
CREATE TABLE material (
    Id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    año VARCHAR(10) NOT NULL
);

-- Tabla: ArticuloGeneral (tabla de relación)
CREATE TABLE articulo_general (
    id SERIAL PRIMARY KEY,
    material_id INTEGER NOT NULL,
    IdUniversidad INTEGER NOT NULL,
    IdAsignatura INTEGER NOT NULL,
    IdProfesor INTEGER NOT NULL,
    CONSTRAINT fk_material FOREIGN KEY (material_id) REFERENCES material(Id) ON DELETE CASCADE,
    CONSTRAINT fk_universidad FOREIGN KEY (IdUniversidad) REFERENCES universidad(Id) ON DELETE RESTRICT,
    CONSTRAINT fk_asignatura FOREIGN KEY (IdAsignatura) REFERENCES asignatura(Id) ON DELETE RESTRICT,
    CONSTRAINT fk_profesor FOREIGN KEY (IdProfesor) REFERENCES profesores(Id) ON DELETE RESTRICT,
    CONSTRAINT uk_articulo_unico UNIQUE (material_id, IdUniversidad, IdAsignatura, IdProfesor)
);

-- Crear índices para optimizar búsquedas
CREATE INDEX idx_articulo_material ON articulo_general(material_id);
CREATE INDEX idx_articulo_universidad ON articulo_general(IdUniversidad);
CREATE INDEX idx_articulo_asignatura ON articulo_general(IdAsignatura);
CREATE INDEX idx_articulo_profesor ON articulo_general(IdProfesor);
