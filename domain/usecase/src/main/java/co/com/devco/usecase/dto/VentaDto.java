package co.com.devco.usecase.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VentaDto {
    private Long idVenta;
    private LocalDateTime fecha;
    private String observacion;
    Long identificacionCliente;
    private List<DetalleVentaDto> productos;
    private double total;
}
