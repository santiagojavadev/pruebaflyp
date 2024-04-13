package com.f2x.transacciones.application.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private TipoTransaccion tipoTransaccion;
    @Column(name = "cuenta_id")
    private String cuenta;
    @Column(name = "cuenta_transferencia_id")
    private String cuentaTransferencia;
    private BigDecimal valor;
    private LocalDateTime fechaActualizacion;
}
