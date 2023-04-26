package co.com.devco.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    @NotNull(message = "El campo 'numeroIdentificacion' es obligatorio")
    private Long numeroIdentificacion;
    @NotBlank(message = "El campo 'nombre' es obligatorio")
    private String nombre;
    @NotBlank(message = "El campo 'telefono' es obligatorio")
    private String telefono;
    @Email(message = "Email con formato invalido")
    private String email;
    private String direccion;
}
