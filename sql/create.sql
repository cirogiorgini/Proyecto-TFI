
DROP DATABASE IF EXISTS empleados_db;
CREATE DATABASE empleados_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE empleados_db;

-- Tabla empleado (A)
CREATE TABLE empleado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    dni VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(120),
    fecha_ingreso DATE,
    area VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE legajo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    nro_legajo VARCHAR(20) NOT NULL UNIQUE,
    categoria VARCHAR(30),
    estado ENUM('ACTIVO','INACTIVO') NOT NULL DEFAULT 'ACTIVO',
    fecha_alta DATE,
    observaciones VARCHAR(255),
    empleado_id BIGINT, -- FK a empleado
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL,
    CONSTRAINT fk_legajo_empleado FOREIGN KEY (empleado_id)
        REFERENCES empleado(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT uq_legajo_empleado UNIQUE (empleado_id)
);

-- Índices útiles
CREATE INDEX idx_empleado_dni ON empleado(dni);
CREATE INDEX idx_legajo_nro ON legajo(nro_legajo);