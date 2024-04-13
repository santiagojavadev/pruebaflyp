package com.f2x.transacciones.application;

import com.f2x.transacciones.application.domain.model.Cuenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public abstract class ValidacionRetiro {

    protected final CuentaService cuentaService;

    protected boolean validarRetiroCuenta(String numeroCuenta, BigDecimal valor){
        BigDecimal saldo = obtenerSaldoCuenta(numeroCuenta);
        return saldo.compareTo(valor) >= 0;
    }

    private BigDecimal obtenerSaldoCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaService.findByNumeroCuenta(numeroCuenta);
        return cuenta.getSaldo();
    }
}
