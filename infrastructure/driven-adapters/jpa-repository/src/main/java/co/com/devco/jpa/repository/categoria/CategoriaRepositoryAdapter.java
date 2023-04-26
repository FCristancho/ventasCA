package co.com.devco.jpa.repository.categoria;

import co.com.devco.jpa.entities.CategoriaDB;
import co.com.devco.jpa.entities.ClienteDB;
import co.com.devco.jpa.helper.AdapterOperations;
import co.com.devco.model.categoria.Categoria;
import co.com.devco.model.cliente.Cliente;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepositoryAdapter extends AdapterOperations<Categoria, CategoriaDB, Long, CategoriaRepository>
        implements co.com.devco.model.categoria.gateways.CategoriaRepository {

    public CategoriaRepositoryAdapter(CategoriaRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Categoria.class));
    }


    @Override
    public Optional<Categoria> obtenerCategoria(Long idCategoria) {
        return Optional.ofNullable(super.findById(idCategoria));
    }

    @Override
    public List<Categoria> obtenerCategorias() {
        return super.findAll();
    }
}
