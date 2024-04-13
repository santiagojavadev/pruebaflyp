package com.f2x.clientes.application;

import com.f2x.clientes.application.domain.model.cuentas.Cuenta;

import java.util.List;

public interface ServicioCuentas {
    List<Cuenta> getCuentasCliente(String tipoId, String numeroId);
}
