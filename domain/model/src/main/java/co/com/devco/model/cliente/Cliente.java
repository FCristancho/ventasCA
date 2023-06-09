package co.com.devco.model.cliente;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static co.com.devco.model.utils.Messages.*;
import static co.com.devco.model.utils.ValidadorArgumento.*;

@Getter
@Setter
@NoArgsConstructor
@Builder(toBuilder = true)
public class Cliente {

    private Long id;
    private Long numeroIdentificacion;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    private boolean activo;

    public Cliente(Long id, Long numeroIdentificacion, String nombre, String telefono, String email, String direccion, boolean activo) {
        validarObligatorio(numeroIdentificacion, SIN_IDENTIFICACION);
        validarObligatorio(nombre, SIN_NOMBRE);
        validarObligatorio(telefono, SIN_TELEFONO);
        validarObligatorio(email, SIN_EMAIL);
        validarEmail(email, EMAIL_INVALIDO);
        validarCadenasTexto(nombre, NOMBRE_INVALIDO);
        validarCadenasTexto(telefono, TELEFONO_INVALIDO);
        this.id = id;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.activo = true;
    }

    public void cambiarEstado(){
        this.activo = !this.activo;
    }
}
