package com.f2x.transacciones.application.saldo;

import com.f2x.transacciones.application.CuentaService;
import com.f2x.transacciones.application.domain.model.ActualizacionSaldo;
import com.f2x.transacciones.application.domain.model.Cuenta;
import com.f2x.transacciones.application.domain.model.Transaccion;
import com.f2x.transacciones.application.ValidacionRetiro;

public class ActualizacionSaldoTransferencia extends ValidacionRetiro implements ActualizacionSaldo {

    public ActualizacionSaldoTransferencia(CuentaService cuentaService) {
        super(cuentaService);
    }

    @Override
    public Cuenta actualizarSaldo(Transaccion transaccion) {
        validarRetiroCuenta(transaccion.getCuenta(), transaccion.getValor());
        //sumar en la cuenta destino
        // validar y restar en la cuenta origen
        var cuentaTx = cuentaService.findByNumeroCuenta(transaccion.getCuentaTransferencia());
        cuentaTx.setSaldo(cuentaTx.getSaldo().add(transaccion.getValor()));
        cuentaService.save(cuentaTx);
        var cuenta = cuentaService.findByNumeroCuenta(transaccion.getCuenta());
        cuenta.setSaldo(cuenta.getSaldo().subtract(transaccion.getValor()));
        return cuentaService.save(cuenta);
    }
}
