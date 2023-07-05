package co.com.devco.model.venta;

import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.cliente.Cliente;
import co.com.devco.model.producto.Producto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Venta {

    private Long id;
    private LocalDateTime fecha;
    private String observacion;
    private Cliente cliente;
    private Cajero cajero;
    private List<DetalleVenta> productos;

    public void eliminarProductosSinCantidad(){
        this.productos = productos.stream()
                .filter(DetalleVenta::hayProductosEnLaCesta)
                .toList();
    }
}
