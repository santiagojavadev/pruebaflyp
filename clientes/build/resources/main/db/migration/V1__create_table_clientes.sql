CREATE TABLE clientes (
    id UUID PRIMARY KEY,
    nro_identificacion TEXT NOT NULL,
    tipo_identificacion VARCHAR(10) NOT NULL,
    nombres VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX idx_identificacion ON clientes (tipo_identificacion,nro_identificacion);