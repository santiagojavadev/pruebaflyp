package com.f2x.clientes.infrastructure.persistence;

import com.f2x.clientes.application.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByTipoIdentificacionAndNumeroIdentificacion(String tipoId, String numeroId);
}
