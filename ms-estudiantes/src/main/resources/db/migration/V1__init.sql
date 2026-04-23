CREATE TABLE estudiantes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    fecha_nacimiento DATE,
    fecha_registro TIMESTAMP NOT NULL
);

