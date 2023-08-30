package co.com.devco.model.venta;

import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.cliente.Cliente;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    @Builder.Default
    private boolean estado = true;

    public void eliminarProductosSinCantidad(){
        this.productos = productos.stream()
                .filter(DetalleVenta::hayProductosEnLaCesta)
                .collect(Collectors.toSet());
    }

    public void quitarDuplicados(){
        Map<Long, DetalleVenta> productosMap = new HashMap<>();
        productos.forEach(detalle -> {
            Long productoId = detalle.getProducto().getId();
            if (productosMap.containsKey(productoId)) {
                productosMap.get(productoId).setCantidad(productosMap.get(productoId).getCantidad() + detalle.getCantidad());
            }
            productosMap.putIfAbsent(productoId, detalle);
        });
        this.productos = new HashSet<>(productosMap.values());
    }

    public void calcularTotal(){
        this.total = productos.stream()
                .mapToDouble(producto -> producto.getPrecio() * producto.getCantidad())
                .reduce(0, Double::sum);
    }
}
