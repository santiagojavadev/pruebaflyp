create table cuentas(
    id UUID PRIMARY KEY,
    tipo_cuenta VARCHAR(100) NOT NULL,
    numero_cuenta VARCHAR(100) NOT NULL CONSTRAINT UQ_nro_cta UNIQUE,
    cliente_nro_id VARCHAR(100) NOT NULL,
    cliente_tipo_id VARCHAR(100) NOT NULL,
    saldo NUMERIC(12,4) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP NOT NULL
);

create table transacciones (
    id UUID PRIMARY KEY,
    cuenta_id VARCHAR(100) NOT NULL,
    cuenta_transferencia_id VARCHAR(100),
    tipo_transaccion VARCHAR(100) NOT NULL,
    valor NUMERIC(12,4) NOT NULL,
    fecha_actualizacion TIMESTAMP NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(numero_cuenta),
    FOREIGN KEY (cuenta_transferencia_id) REFERENCES cuentas(numero_cuenta)
);

create index idx_cliente_cuenta on cuentas (cliente_tipo_id,cliente_nro_id);

create unique index idx_nro_cuenta on cuentas (numero_cuenta);