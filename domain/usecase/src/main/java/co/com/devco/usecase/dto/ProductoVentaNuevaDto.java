package co.com.devco.usecase.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductoVentaNuevaDto {

    private Long idProducto;
    private int cantidad;
    private double precio;
}
