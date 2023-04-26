package co.com.devco.model.cliente.gateways;

import co.com.devco.model.cliente.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    List<Cliente> obtenerClientes();
    Cliente guardarCliente(Cliente cliente);

    Optional<Cliente> obtenerCliente(Long id);

    boolean existeIdentificacion(Long numeroIdentificacion);
}
