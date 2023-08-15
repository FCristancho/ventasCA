package co.com.devco.model.venta;

import co.com.devco.model.producto.Producto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private double precio;

    public boolean hayProductosEnLaCesta(){
        return this.cantidad > 0;
    }
}
