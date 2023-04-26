package co.com.devco.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CATEGORIAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CategoriaDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;
}
