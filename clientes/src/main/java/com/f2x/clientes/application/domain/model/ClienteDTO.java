package com.f2x.clientes.application.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

@Data
public class ClienteDTO {

    public static final String FORMATO_FECHA = "yyyy-MM-dd";
    public static final DateTimeFormatter formateador = DateTimeFormatter.ofPattern(FORMATO_FECHA);

    private UUID id;

    @NotBlank(message = "tipo de identificacion es requerido")
    private String tipoIdentificacion;

    @NotBlank(message = "numero de identificacion es requerido")
    private String numeroIdentificacion;

    @NotBlank(message = "nombres del cliente es requerido")
    @Size(min = 2, max = 254, message = "nombres debe ser igual o mayor a 2 caracteres y no puede ser mayor a 254 caracteres")
    private String nombres;

    @NotBlank(message = "apellido del cliente es requerido")
    @Size(min = 2, max = 254, message = "apellido debe ser igual o mayor a 2 caracteres y no puede ser mayor a 254 caracteres")
    private String apellido;

    @NotBlank(message = "correo del cliente es requerido")
    @Email(message = "el correo se encuentra mal escrito")
    @Size(min = 2, max = 254, message = "mail no debe ser mayor a 254 caracteres")
    private String correoElectronico;

    @NotBlank(message = "la fecha de nacimiento es requerida yyyy-mm-dd")
    private String fechaNacimiento;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public Cliente toEntity(){
        return Cliente.builder().id(id)
                .apellido(apellido).correoElectronico(correoElectronico).fechaNacimiento(LocalDate.parse(fechaNacimiento,formateador))
                .nombres(nombres).numeroIdentificacion(numeroIdentificacion).tipoIdentificacion(tipoIdentificacion)
                .fechaActualizacion(fechaActualizacion).fechaCreacion(fechaCreacion)
                .build();
    }
}
