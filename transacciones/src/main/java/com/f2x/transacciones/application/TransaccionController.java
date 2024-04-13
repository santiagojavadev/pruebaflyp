package com.f2x.transacciones.application;

import com.f2x.transacciones.application.domain.model.Transaccion;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    @PostMapping
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion){
        return transaccionService.crearTransaccion(transaccion);
    }
}
