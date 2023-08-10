package co.com.devco.usecase.mapper;

import co.com.devco.model.venta.DetalleVenta;
import co.com.devco.usecase.dto.DetalleVentaDto;
import co.com.devco.usecase.dto.ProductoDto;

import java.util.List;

public class DetalleVentaMapper {

    private DetalleVentaMapper() {
    }

    public static DetalleVentaDto toDetalleVentaDto(DetalleVenta detalleVenta){
        ProductoDto productoDto = ProductoMapper.toProductoDto(detalleVenta.getProducto());
        return new DetalleVentaDto(productoDto, detalleVenta.getCantidad(), detalleVenta.getPrecio());
    }

    public static List<DetalleVentaDto> toListDetalleVentaDto(List<DetalleVenta> detalleVentaList){
        return detalleVentaList.stream().map(DetalleVentaMapper::toDetalleVentaDto).toList();
    }
}
