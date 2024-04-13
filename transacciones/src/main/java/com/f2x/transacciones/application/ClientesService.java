package com.f2x.transacciones.application;

import com.f2x.transacciones.application.domain.model.Cliente;

import java.util.Optional;

public interface ClientesService {
    Optional<Cliente> getCliente(String tipoIdentificacionCliente, String numeroIdentificacionCliente);
}
