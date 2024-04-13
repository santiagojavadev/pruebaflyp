package com.f2x.transacciones.application;

import com.f2x.transacciones.application.domain.model.Cuenta;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping("/documento")
    public List<Cuenta> getCuentasCliente(@RequestParam("tipo") String tipoIdCliente, @RequestParam("numero") String numeroIdCliente){
        return cuentaService.findByTipoYNroDocumentoCliente(tipoIdCliente,numeroIdCliente);
    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.crearNuevaCuenta(cuenta);
    }

    @PutMapping
    public Cuenta actualizarCuenta(@PathVariable("id") UUID id, @RequestBody Cuenta cuenta){
        return cuentaService.save(cuenta);
    }
}
