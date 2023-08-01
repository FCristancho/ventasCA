package co.com.devco.usecase.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductoDto {
    private String nombre;
    private double precio;
}
