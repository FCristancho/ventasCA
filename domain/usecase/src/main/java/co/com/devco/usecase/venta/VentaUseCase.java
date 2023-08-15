package co.com.devco.usecase.venta;

import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.cliente.Cliente;
import co.com.devco.model.cliente.gateways.ClienteRepository;
import co.com.devco.model.producto.Producto;
import co.com.devco.model.venta.DetalleVenta;
import co.com.devco.model.venta.Venta;
import co.com.devco.model.venta.gateways.VentaRepository;
import co.com.devco.usecase.cajero.CajeroUseCase;
import co.com.devco.usecase.cliente.ClienteUseCase;
import co.com.devco.usecase.dto.ProductoVentaNuevaDto;
import co.com.devco.usecase.dto.VentaDto;
import co.com.devco.usecase.dto.VentaNuevaDto;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import co.com.devco.usecase.mapper.ProductoMapper;
import co.com.devco.usecase.mapper.VentaMapper;
import co.com.devco.usecase.producto.ProductoUseCase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static co.com.devco.model.utils.Messages.VENTA_NO_ENCONTRADA;

@RequiredArgsConstructor
public class VentaUseCase {

    private final VentaRepository ventaRepository;
    private final ClienteUseCase clienteUseCase;
    private final CajeroUseCase cajeroUseCase;
    private final ProductoUseCase productoUseCase;

    public List<VentaDto> obtenerVentas(){
        List<Venta> venta = this.ventaRepository.obtenerVentas();
        return VentaMapper.toListVentaDto(venta);
    }

    public VentaDto obtenerVentaPorId(Long id){
        Venta venta = this.ventaRepository.obtenerVenta(id).
                orElseThrow(() -> new ExcepcionNoEncontrado(VENTA_NO_ENCONTRADA));
        return VentaMapper.toVentaDto(venta);
    }

    public VentaDto guardarVenta(VentaNuevaDto ventaNueva){
        Cliente cliente = clienteUseCase.obtenerClientePorIdentificacion(ventaNueva.getIdentificacionCliente());
        Cajero cajero = cajeroUseCase.obtenerCajero(ventaNueva.getIdCajero());
        Set<DetalleVenta> detalleVenta = obtenerListaProductos(ventaNueva.getProductos());
        Venta venta = Venta.builder()
                .observacion(ventaNueva.getObservacion())
                .cliente(cliente)
                .cajero(cajero)
                .productos(detalleVenta)
                .build();
        venta.eliminarProductosSinCantidad();
        venta.quitarDuplicados();
        venta.calcularTotal();
        System.out.println(venta);

        return VentaMapper.toVentaDto(venta);
    }

    private Set<DetalleVenta> obtenerListaProductos(List<ProductoVentaNuevaDto> productos){
        Set<DetalleVenta> productosVenta = new HashSet<>();
        productos.forEach(producto -> {
            Producto productoEncontrado = productoUseCase.obtenerProducto(producto.getIdProducto());
            DetalleVenta detalle = ProductoMapper.toDetalleVenta(producto);
            detalle.setProducto(productoEncontrado);
            productosVenta.add(detalle);
        });

        return productosVenta;
    }
}
