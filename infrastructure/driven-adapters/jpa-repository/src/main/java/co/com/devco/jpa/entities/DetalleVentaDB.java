package co.com.devco.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DETALLE_VENTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DetalleVentaDB {

    @EmbeddedId
    private DetalleVentaPK id;

    @ManyToOne
    @MapsId("ventaId")
    @JoinColumn(name = "venta_id")
    private VentaDB venta;
    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoDB producto;

    private int cantidad;
    private double precio;

    private boolean estado;
}
