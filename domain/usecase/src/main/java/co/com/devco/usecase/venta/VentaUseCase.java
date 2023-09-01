package co.com.devco.usecase.venta;

import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.cliente.Cliente;
import co.com.devco.model.producto.Producto;
import co.com.devco.model.venta.DetalleVenta;
import co.com.devco.model.venta.Venta;
import co.com.devco.model.venta.gateways.DetalleVentaRepository;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static co.com.devco.model.utils.Messages.VENTA_NO_ENCONTRADA;

@RequiredArgsConstructor
public class VentaUseCase {

    private final VentaRepository ventaRepository;
    private final ClienteUseCase clienteUseCase;
    private final CajeroUseCase cajeroUseCase;
    private final ProductoUseCase productoUseCase;
    private final DetalleVentaRepository detalleVentaRepository;

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
        validarProductosVenta(ventaNueva.getProductos());
        Cliente cliente = clienteUseCase.obtenerClientePorIdentificacion(ventaNueva.getIdentificacionCliente());
        Cajero cajero = cajeroUseCase.obtenerCajero(ventaNueva.getIdCajero());

        Venta venta = this.construirVenta(ventaNueva, cliente, cajero);
        construirDetalleVenta(ventaNueva, venta);

        Venta ventadb = ventaRepository.guardarVenta(venta);
        guardarDetalleVenta(ventadb);

        return VentaMapper.toVentaDto(ventadb);
    }

    private void validarProductosVenta(List<ProductoVentaNuevaDto> productos){
        productos.forEach(producto ->{
            productoUseCase.validarExistenciaProducto(producto.getIdProducto());
            productoUseCase.actualizarStock(producto.getIdProducto(), producto.getCantidad(), Boolean.TRUE);
        });
    }

    public Venta construirVenta(VentaNuevaDto ventaNueva, Cliente cliente, Cajero cajero){
        return Venta.builder()
                .observacion(ventaNueva.getObservacion())
                .cliente(cliente)
                .cajero(cajero)
                .build();
    }

    public Venta construirDetalleVenta(VentaNuevaDto ventaNueva, Venta venta){
        Set<DetalleVenta> detalleVenta = obtenerListaProductos(ventaNueva.getProductos(), venta);
        venta.setProductos(detalleVenta);
        venta.eliminarProductosSinCantidad();
        venta.quitarDuplicados();
        venta.calcularTotal();

        return venta;
    }

    private Set<DetalleVenta> obtenerListaProductos(List<ProductoVentaNuevaDto> productos, Venta venta){
        Set<DetalleVenta> productosVenta = new HashSet<>();
        productos.forEach(producto -> {
            Producto productoEncontrado = productoUseCase.obtenerProducto(producto.getIdProducto());
            DetalleVenta detalle = ProductoMapper.toDetalleVenta(producto);
            detalle.setProducto(productoEncontrado);
            detalle.setVenta(venta);
            productosVenta.add(detalle);
        });

        return productosVenta;
    }

    private void guardarDetalleVenta(Venta venta){
        venta.getProductos().forEach(
            detalleVentaRepository::guardarDetalleVenta
        );
    }
}