package com.f2x.transacciones.application.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private TipoCuenta tipoCuenta;
    private String numeroCuenta;
    @Column(name = "cliente_tipo_id")
    private String tipoIdentificacionCliente;
    @Column(name = "cliente_nro_id")
    private String numeroIdentificacionCliente;
    private BigDecimal saldo;
    @Column(name = "estado")
    private EstadoCuenta estadoCuenta;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

}
