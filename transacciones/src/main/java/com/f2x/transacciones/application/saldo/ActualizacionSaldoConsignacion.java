package com.f2x.transacciones.application.saldo;

import com.f2x.transacciones.application.CuentaService;
import com.f2x.transacciones.application.domain.model.ActualizacionSaldo;
import com.f2x.transacciones.application.domain.model.Cuenta;
import com.f2x.transacciones.application.domain.model.Transaccion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActualizacionSaldoConsignacion implements ActualizacionSaldo {

    private final CuentaService cuentaService;

    @Override
    public Cuenta actualizarSaldo(Transaccion transaccion) {
        //sumar saldo en cuenta
        var cuenta = cuentaService.findByNumeroCuenta(transaccion.getCuenta());
        cuenta.setSaldo(cuenta.getSaldo().add(transaccion.getValor()));
        return cuentaService.save(cuenta);
    }
}
