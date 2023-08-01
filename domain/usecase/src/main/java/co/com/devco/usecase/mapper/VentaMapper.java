package co.com.devco.usecase.mapper;

import co.com.devco.model.venta.Venta;
import co.com.devco.usecase.dto.DetalleVentaDto;
import co.com.devco.usecase.dto.VentaDto;

import java.util.List;

public class VentaMapper {

    private VentaMapper (){}

    public static VentaDto toVentaDto(Venta venta){
        List<DetalleVentaDto> ventaDtoList = DetalleVentaMapper.toListDetalleVentaDto(venta.getProductos().stream().toList());
        return VentaDto.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .observacion(venta.getObservacion())
                .idCliente(venta.getCliente().getId())
                .productos(ventaDtoList)
                .total(venta.getTotal())
                .build();
    }

    public static List<VentaDto> toListVentaDto(List<Venta> ventaList){
        return ventaList.stream().map(VentaMapper::toVentaDto).toList();
    }
}
