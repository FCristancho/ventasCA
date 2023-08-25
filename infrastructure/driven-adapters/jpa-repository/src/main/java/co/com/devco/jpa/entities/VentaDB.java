package co.com.devco.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteDB cliente;
    @ManyToOne(optional = false)
    private CajeroDB cajero;

    @OneToMany(mappedBy = "venta")
    private Set<DetalleVentaDB> productos;
    private double total;
    private boolean estado;


    @PrePersist
    private void prePersist() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
        estado = true;
    }
}
