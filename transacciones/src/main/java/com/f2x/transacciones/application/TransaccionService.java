package com.f2x.transacciones.application;

import com.f2x.transacciones.application.domain.model.ActualizacionSaldo;
import com.f2x.transacciones.application.domain.model.TipoTransaccion;
import com.f2x.transacciones.application.domain.model.Transaccion;
import com.f2x.transacciones.application.saldo.ActualizacionSaldoConsignacion;
import com.f2x.transacciones.application.saldo.ActualizacionSaldoRetiro;
import com.f2x.transacciones.application.saldo.ActualizacionSaldoTransferencia;
import com.f2x.transacciones.infrastructure.persistence.TransaccionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final CuentaService cuentaService;

    @Transactional
    public Transaccion crearTransaccion(Transaccion transaccion) {
        actualizarSaldos(transaccion);
        transaccion.setFechaActualizacion(LocalDateTime.now());
        return transaccionRepository.save(transaccion);
    }

    private void actualizarSaldos(Transaccion transaccion) {
        ActualizacionSaldo actualizacionSaldo = obtenerActualizacionEspecifica(transaccion.getTipoTransaccion());
        actualizacionSaldo.actualizarSaldo(transaccion);
    }

    private ActualizacionSaldo obtenerActualizacionEspecifica(TipoTransaccion tipoTransaccion){
        switch (tipoTransaccion){
            case TRANSFERENCIA: return new ActualizacionSaldoTransferencia(cuentaService);
            case RETIRO: return new ActualizacionSaldoRetiro(cuentaService);
            case CONSIGNACION: return new ActualizacionSaldoConsignacion(cuentaService);
            default: throw new RuntimeException("Tipo de operacion no identificado");
        }
    }
}
