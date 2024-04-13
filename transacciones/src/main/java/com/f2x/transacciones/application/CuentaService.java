package com.f2x.transacciones.application;

import com.f2x.transacciones.application.domain.model.Cuenta;
import com.f2x.transacciones.application.domain.model.exception.NoEncontradoException;
import com.f2x.transacciones.infrastructure.persistence.CuentasRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class CuentaService {

    private final CuentasRepository cuentasRepository;

    private final ClientesService clientesService;

    //@Cacheable(value = "redis_cache", key = "#numeroCuenta")
    public Cuenta findByNumeroCuenta(String numeroCuenta){
        return cuentasRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(()->new NoEncontradoException("No se encuentra la cuenta con numero: "+numeroCuenta));
    }

    //@Cacheable("redis_cache")
    public List<Cuenta> findByTipoYNroDocumentoCliente(String tipoDocumentoCliente, String numeroDocumentoCliente){
        return cuentasRepository.findByTipoIdentificacionClienteAndNumeroIdentificacionCliente(tipoDocumentoCliente,numeroDocumentoCliente);
                //.orElseThrow(()->new NoEncontradoException("No se encuentra la cuenta del cliente con documento: "+tipoDocumentoCliente+numeroDocumentoCliente));
    }

    //@CacheEvict(value = "redis_cache", key = "#cuenta.numeroCuenta")
    public Cuenta save(Cuenta cuenta) {
        cuenta.setFechaActualizacion(LocalDateTime.now());
        return cuentasRepository.save(cuenta);
    }

    public Cuenta crearNuevaCuenta(Cuenta cuenta) {
        validarCliente(cuenta.getTipoIdentificacionCliente(),cuenta.getNumeroIdentificacionCliente());
        cuenta.setFechaCreacion(LocalDateTime.now());
        cuenta.setFechaActualizacion(LocalDateTime.now());
        return cuentasRepository.save(cuenta);
    }

    private void validarCliente(String tipoIdentificacionCliente, String numeroIdentificacionCliente) {
        var cliente = clientesService.getCliente(tipoIdentificacionCliente,numeroIdentificacionCliente);
        if(cliente.isEmpty()){
            throw new NoEncontradoException("Cliente no existe para el documento:"+tipoIdentificacionCliente+numeroIdentificacionCliente);
        }
    }
}
