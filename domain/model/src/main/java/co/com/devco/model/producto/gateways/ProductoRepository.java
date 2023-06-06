package co.com.devco.model.producto.gateways;

import co.com.devco.model.producto.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {

    Optional<Producto> obtenerProducto(Long idProducto);
    List<Producto> obtenerProductos();
    Producto guardarProducto(Producto producto);
}
