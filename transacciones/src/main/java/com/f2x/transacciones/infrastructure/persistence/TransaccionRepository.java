package com.f2x.transacciones.infrastructure.persistence;

import com.f2x.transacciones.application.domain.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransaccionRepository extends JpaRepository<Transaccion, UUID> {
}
