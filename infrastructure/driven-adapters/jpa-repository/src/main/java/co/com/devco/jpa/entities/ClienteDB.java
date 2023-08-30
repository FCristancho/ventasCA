package co.com.devco.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "CLIENTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClienteDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_identificacion", unique = true, nullable = false)
    private Long numeroIdentificacion;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String email;
    private String direccion;
    @Column(nullable = false)
    private boolean activo;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cliente")
    private Set<VentaDB> compras;
}
