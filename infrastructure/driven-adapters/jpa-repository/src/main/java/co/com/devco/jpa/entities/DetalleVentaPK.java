package co.com.devco.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaPK implements Serializable {

    @Column(name = "venta_id")
    private Long ventaId;
    @Column(name = "producto_id")
    private Long productoId;
}
