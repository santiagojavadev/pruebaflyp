package com.f2x.transacciones.infrastructure;

import com.f2x.transacciones.application.ClientesService;
import com.f2x.transacciones.application.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientesRestService implements ClientesService {

    private static final String URL = "http://localhost:8081/api/clientes/documento?";

    private final RestTemplate restTemplate;

    private Optional<Cliente> obtenerRecursoDeServicioCliente(String tipo, String numero) {
        var url = URL + "tipo=" + tipo + "&numero=" + numero;
        Optional<Cliente> cliente = Optional.empty();
        try {
            cliente = Optional.ofNullable(restTemplate.getForObject(url, Cliente.class));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // handle 404
                cliente = Optional.empty();
            }
        }
        return cliente;
    }

    @Override
    public Optional<Cliente> getCliente(String tipoIdentificacionCliente, String numeroIdentificacionCliente) {
        return obtenerRecursoDeServicioCliente(tipoIdentificacionCliente,numeroIdentificacionCliente);
    }
}
