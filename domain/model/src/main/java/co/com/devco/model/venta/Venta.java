package co.com.devco.model.venta;

import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.cliente.Cliente;
import co.com.devco.model.producto.Producto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<DetalleVenta> productos;
    private double total;
    private boolean estado;

    public void eliminarProductosSinCantidad(){
        this.productos = productos.stream()
                .filter(DetalleVenta::hayProductosEnLaCesta)
                .collect(Collectors.toSet());
    }
}
