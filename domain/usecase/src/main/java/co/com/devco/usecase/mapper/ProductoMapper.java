package co.com.devco.usecase.mapper;

import co.com.devco.model.producto.Producto;
import co.com.devco.model.venta.DetalleVenta;
import co.com.devco.usecase.dto.ProductoDto;
import co.com.devco.usecase.dto.ProductoVentaNuevaDto;

public class ProductoMapper {

    private ProductoMapper() {
    }

    public static ProductoDto toProductoDto(Producto producto){
        return new ProductoDto(producto.getNombre(), producto.getPrecio());
    }

    public static DetalleVenta toDetalleVenta(ProductoVentaNuevaDto producto){
        return DetalleVenta.builder().
                cantidad(producto.getCantidad())
                .precio(producto.getPrecio())
                .build();
    }
}
