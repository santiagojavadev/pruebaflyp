package com.f2x.clientes.infrastructure.rest;

import com.f2x.clientes.application.ServicioCuentas;
import com.f2x.clientes.application.domain.model.cuentas.Cuenta;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class ServicioCuentasRest implements ServicioCuentas {

    private static final String URL = "http://localhost:8080/api/cuentas/documento?";

    private final RestTemplate restTemplate;

    private List<Cuenta> obtenerRecursoDeServicioCuentas(String tipo, String numero) {
        var url = URL + "tipo=" + tipo + "&numero=" + numero;

        ResponseEntity<List<Cuenta>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Error consumiendo servicio de cuentas");
        }
    }

    @Override
    public List<Cuenta> getCuentasCliente(String tipoId, String numeroId) {
        return obtenerRecursoDeServicioCuentas(tipoId,numeroId);
    }
}
