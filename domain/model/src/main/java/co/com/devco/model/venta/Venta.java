package co.com.devco.model.venta;

import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.cliente.Cliente;
import co.com.devco.model.producto.Producto;
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
@ToString
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

    public void calcularTotal(){
        this.total = productos.stream()
                .mapToDouble(producto -> producto.getPrecio() * producto.getCantidad())
                .reduce(0, Double::sum);
    }

    public void quitarDuplicados(){
        Map<Long, DetalleVenta> productosMap = new HashMap<>();
        productos.forEach( detalle -> {
            DetalleVenta producto = productosMap.get(detalle.getProducto().getId());
            if(producto != null){
                producto.setCantidad(producto.getCantidad() + detalle.getCantidad());
                productosMap.put(detalle.getProducto().getId(), producto);
            }else{
                productosMap.put(detalle.getProducto().getId(), detalle);
            }
        });
        this.productos = new HashSet<>(productosMap.values());
        //TODO se puede realizar con mapas
    }
}
