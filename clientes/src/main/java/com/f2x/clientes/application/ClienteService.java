package com.f2x.clientes.application;

import com.f2x.clientes.application.domain.model.Cliente;
import com.f2x.clientes.application.domain.model.ClienteDTO;
import com.f2x.clientes.application.domain.model.cuentas.Cuenta;
import com.f2x.clientes.application.domain.model.exceptions.NoEncontradoException;
import com.f2x.clientes.application.domain.model.exceptions.ValidacionRuntimeException;
import com.f2x.clientes.infrastructure.persistence.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ServicioCuentas servicioCuentas;

    public Cliente crearCliente(ClienteDTO cliente) {
        validarFechaNacimiento(cliente);
        if(clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(cliente.getTipoIdentificacion(), cliente.getNumeroIdentificacion()).isPresent()){
            throw new ValidacionRuntimeException("Documento del cliente ya existe:"+cliente.getTipoIdentificacion()+ cliente.getNumeroIdentificacion());
        }
        cliente.setId(UUID.randomUUID());
        cliente.setFechaCreacion(LocalDateTime.now());
        cliente.setFechaActualizacion(LocalDateTime.now());
        return clienteRepository.save(cliente.toEntity());
    }

    private void validarFechaNacimiento(ClienteDTO cliente) {
        LocalDate.parse(cliente.getFechaNacimiento(),cliente.formateador);
    }

    public Cliente actualizarCliente(UUID id, Map<String, String> camposActualizar) {
        AtomicReference<Cliente> clienteAtomicReference = new AtomicReference<>();
        clienteRepository.findById(id).ifPresentOrElse(clienteBaseDatos -> {
                    clienteBaseDatos.setFechaActualizacion(LocalDateTime.now());
            clienteAtomicReference.set(clienteBaseDatos);
                }, () -> { throw new ValidacionRuntimeException("Cliente a actualizar no encontrado");}
        );
        var cliente = clienteAtomicReference.get();
        setCamposActualizar(cliente,camposActualizar);
        return clienteRepository.save(cliente);
    }

    private void setCamposActualizar(Cliente cliente, Map<String, String> camposActualizar) {
        var cuentaFields = Arrays.stream(cliente.getClass().getDeclaredFields()).map(field -> field.getName()).toList();
        var camposNoEncontrados = camposActualizar.keySet().stream().filter( campo-> !cuentaFields.contains(campo))
                .toList();
        if(!camposNoEncontrados.isEmpty()){
            var camposError = camposNoEncontrados.stream().collect(Collectors.joining(","));
            throw new ValidacionRuntimeException("Error al hacer update con campos: "+camposError);
        }

    }

    public void eliminarCliente(UUID id) {
        clienteRepository.findById(id).ifPresentOrElse(clienteBaseDatos -> {
                validarCuentas(clienteBaseDatos.getTipoIdentificacion(),clienteBaseDatos.getNumeroIdentificacion());
                clienteRepository.deleteById(id);
            }, () -> { throw new ValidacionRuntimeException("Cliente a eliminar no encontrado");});
    }

    private void validarCuentas(String tipoIdentificacion, String numeroIdentificacion) {
        List<Cuenta> cuentas = servicioCuentas.getCuentasCliente(tipoIdentificacion,numeroIdentificacion);
        if(!cuentas.isEmpty()){
            throw new ValidacionRuntimeException("Cliente no se puede eliminar ya que tiene cuentas asociadas");
        }
    }

    public Cliente getClienteByDocumento(String tipoId, String numeroId){
        return clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoId,numeroId)
                .orElseThrow(()-> new NoEncontradoException("Cliente no encontrado con id:"+tipoId+numeroId));
    }
}
