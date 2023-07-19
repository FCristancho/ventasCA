package co.com.devco.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "VENTAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VentaDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private LocalDateTime fecha;
    private String observacion;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private ClienteDB cliente;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private CajeroDB cajero;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVentaDB> detalleVenta;

    private double total;

    @PrePersist
    private void prePersist() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
    }
}
