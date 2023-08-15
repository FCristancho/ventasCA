package co.com.devco.jpa.repository.producto;

import co.com.devco.jpa.entities.ProductoDB;
import co.com.devco.jpa.helper.AdapterOperations;
import co.com.devco.model.producto.Producto;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductoRepositoryAdapter extends AdapterOperations<Producto, ProductoDB, Long, ProductoRepository> implements co.com.devco.model.producto.gateways.ProductoRepository{
    public ProductoRepositoryAdapter(ProductoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Producto.class));
    }

    @Override
    public Optional<Producto> obtenerProducto(Long idProducto) {
        return Optional.ofNullable(findById(idProducto));
    }

    @Override
    public List<Producto> obtenerProductos() {
        return findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return save(producto);
    }

    @Override
    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }
}
