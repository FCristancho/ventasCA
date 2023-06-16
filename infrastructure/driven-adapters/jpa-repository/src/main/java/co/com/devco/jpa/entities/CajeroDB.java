package co.com.devco.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CAJEROS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CajeroDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telefono;
}
