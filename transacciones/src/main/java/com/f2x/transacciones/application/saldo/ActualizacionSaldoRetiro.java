package com.f2x.transacciones.application.saldo;

import com.f2x.transacciones.application.CuentaService;
import com.f2x.transacciones.application.domain.model.ActualizacionSaldo;
import com.f2x.transacciones.application.domain.model.Cuenta;
import com.f2x.transacciones.application.domain.model.Transaccion;
import com.f2x.transacciones.application.ValidacionRetiro;
import org.springframework.stereotype.Service;

@Service
public class ActualizacionSaldoRetiro extends ValidacionRetiro implements ActualizacionSaldo {


    public ActualizacionSaldoRetiro(CuentaService cuentaService) {
        super(cuentaService);
    }

    @Override
    public Cuenta actualizarSaldo(Transaccion transaccion) {
        validarRetiroCuenta(transaccion.getCuenta(), transaccion.getValor());
        var cuenta = cuentaService.findByNumeroCuenta(transaccion.getCuenta());
        cuenta.setSaldo(cuenta.getSaldo().subtract(transaccion.getValor()));
        return cuentaService.save(cuenta);
    }
}
