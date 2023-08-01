package co.com.devco.usecase.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClienteDto {

    private Long id;
    private Long numeroIdentificacion;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private boolean activo;
}
