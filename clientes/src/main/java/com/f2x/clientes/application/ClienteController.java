package com.f2x.clientes.application;

import com.f2x.clientes.application.domain.model.Cliente;
import com.f2x.clientes.application.domain.model.ClienteDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public Cliente crearCliente(@Validated @RequestBody ClienteDTO cliente) {
        return clienteService.crearCliente(cliente);
    }

    @PatchMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable("id") UUID id, @RequestBody Map<String,String> camposActualizar) {
        return clienteService.actualizarCliente(id, camposActualizar);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable("id") UUID id) {
        clienteService.eliminarCliente(id);
    }

    @GetMapping("/documento")
    public Cliente getClienteByTipoYNumeroId(@RequestParam("tipo") String tipoId, @RequestParam("numero") String numeroId){
        return clienteService.getClienteByDocumento(tipoId,numeroId);
    }
}
