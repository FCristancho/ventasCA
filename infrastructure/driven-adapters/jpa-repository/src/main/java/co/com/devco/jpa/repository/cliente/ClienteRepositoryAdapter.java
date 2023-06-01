package co.com.devco.jpa.repository.cliente;

import co.com.devco.jpa.entities.ClienteDB;
import co.com.devco.jpa.helper.AdapterOperations;
import co.com.devco.model.cliente.Cliente;
import jakarta.transaction.Transactional;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryAdapter extends AdapterOperations<Cliente, ClienteDB, Long, ClienteRepository> implements co.com.devco.model.cliente.gateways.ClienteRepository {

    public ClienteRepositoryAdapter(ClienteRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Cliente.class));
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return findAll();
    }

    @Transactional
    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return save(cliente);
    }

    @Override
    public Optional<Cliente> obtenerCliente(Long id) {
        return Optional.ofNullable(findById(id));
    }

    @Override
    public boolean existeIdentificacion(Long numeroIdentificacion) {
        return repository.existsByNumeroIdentificacion(numeroIdentificacion);
    }
}
