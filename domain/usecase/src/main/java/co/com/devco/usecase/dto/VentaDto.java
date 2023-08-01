package co.com.devco.usecase.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VentaDto {
    private Long id;
    private LocalDateTime fecha;
    private String observacion;
    Long idCliente;
    private List<DetalleVentaDto> productos;
    private double total;
}
