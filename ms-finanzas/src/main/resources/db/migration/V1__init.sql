CREATE TABLE aranceles (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           estudiante_rut VARCHAR(100),
                           nombre VARCHAR(100),
                           monto DOUBLE,
                           fecha VARCHAR(50),
                           estado VARCHAR(50)
);

CREATE TABLE becas (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(100),
                       porcentaje DOUBLE
);

CREATE TABLE convenios_pago (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                cuotas INT,
                                monto_total DOUBLE
);