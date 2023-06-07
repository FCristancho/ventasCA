package co.com.devco.usecase.producto;

import co.com.devco.model.producto.Producto;
import co.com.devco.model.producto.gateways.ProductoRepository;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static co.com.devco.usecase.utils.Messages.PRODUCTO_NO_ENCONTRADO;

@RequiredArgsConstructor
public class ProductoUseCase {


    private final ProductoRepository productoRepository;

    public Producto obtenerProducto(Long id){
        return this.productoRepository.obtenerProducto(id).orElseThrow(
                () -> new ExcepcionNoEncontrado(PRODUCTO_NO_ENCONTRADO)
        );
    }

    public List<Producto> obtenerProductos(){
        return this.productoRepository.obtenerProductos();
    }

    public Producto actualizarStock(Long idProducto, int cantidad, boolean descontar) {
        Producto producto = this.obtenerProducto(idProducto);
        if (descontar) {
            producto.descontarStock(cantidad);
        } else {
            producto.devolverStock(cantidad);
        }
        return productoRepository.guardarProducto(producto);
    }
}
