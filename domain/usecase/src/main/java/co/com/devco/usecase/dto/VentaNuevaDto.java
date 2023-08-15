package co.com.devco.usecase.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class VentaNuevaDto {

    private Long identificacionCliente;
    private Long idCajero;
    private List<ProductoVentaNuevaDto> productos;
    private String observacion;
}
