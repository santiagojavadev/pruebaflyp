package com.f2x.transacciones.infrastructure.persistence;

import com.f2x.transacciones.application.domain.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CuentasRepository extends JpaRepository<Cuenta, UUID> {

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    List<Cuenta> findByTipoIdentificacionClienteAndNumeroIdentificacionCliente(String tipoIdCliente, String numeroIdCliente);
}
