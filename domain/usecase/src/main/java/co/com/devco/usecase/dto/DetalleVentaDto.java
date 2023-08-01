package co.com.devco.usecase.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DetalleVentaDto {
    private ProductoDto producto;
    private int cantidad;
    private double precio;
}
