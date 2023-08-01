package co.com.devco.usecase.mapper;

import co.com.devco.model.producto.Producto;
import co.com.devco.usecase.dto.ProductoDto;
import lombok.*;

public class ProductoMapper {

    public static ProductoDto toProductoDto(Producto producto){
        return new ProductoDto(producto.getNombre(), producto.getPrecio());
    }
}
