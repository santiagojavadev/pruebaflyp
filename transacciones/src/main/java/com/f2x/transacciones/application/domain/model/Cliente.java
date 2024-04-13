package com.f2x.transacciones.application.domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Cliente {
    private UUID id;

    private String tipoIdentificacion;

    private String numeroIdentificacion;
    private String nombres;
    private String apellido;
    private String correoElectronico;
    private LocalDate fechaNacimiento;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
