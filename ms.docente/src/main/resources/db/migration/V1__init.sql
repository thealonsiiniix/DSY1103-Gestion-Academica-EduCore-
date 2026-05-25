CREATE TABLE docentes (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          rut VARCHAR(20) UNIQUE,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100) NOT NULL,
                          correo VARCHAR(150) UNIQUE
);

INSERT INTO docentes (rut, nombre, apellido, correo) VALUES
                                                         ('11111111-1', 'Carlos', 'Muñoz', 'carlos@instituto.cl'),
                                                         ('22222222-2', 'María', 'González', 'maria@instituto.cl'),
                                                         ('33333333-3', 'Pedro', 'Rojas', 'pedro@instituto.cl'),
                                                         ('44444444-4', 'Ana', 'Contreras', 'ana@instituto.cl'),
                                                         ('55555555-5', 'Luis', 'Pérez', 'luis@instituto.cl');