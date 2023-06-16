package co.com.devco.jpa.repository.cajero;

import co.com.devco.jpa.entities.CajeroDB;
import co.com.devco.jpa.entities.CategoriaDB;
import co.com.devco.jpa.helper.AdapterOperations;
import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.categoria.Categoria;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CajeroRepositoryAdapter extends AdapterOperations<Cajero, CajeroDB, Long, CajeroRepository>
        implements co.com.devco.model.cajero.gateways.CajeroRepository {

    public CajeroRepositoryAdapter(CajeroRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Cajero.class));
    }


    @Override
    public Optional<Cajero> obtenerCajero(Long id) {
        return Optional.ofNullable(super.findById(id));
    }

    @Override
    public List<Cajero> obtenerCajeros() {
        return findAll();
    }
}
