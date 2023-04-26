package co.com.devco.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespuestaError {

    private String mensaje;
    private Object detalles;
}
