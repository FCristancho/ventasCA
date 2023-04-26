package co.com.devco.model.cliente;

import co.com.devco.model.exception.ExcepcionEmail;
import co.com.devco.model.exception.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private static final Long IDENTIFICACION = 123456789L;
    private static final String NOMBRE = "Juan Perez";
    private static final String TELEFONO = "5551234";
    private static final String EMAIL_VALIDO = "juanperez@example.com";
    private static final String EMAIL_INVALIDO = "juanperezexample.com";
    private static final String DIRECCION = "Calle 123";

    @Test
    @DisplayName("Cliente válido")
    void testClienteValido() {
        Cliente cliente = Cliente.builder()
                .id(1L)
                .numeroIdentificacion(IDENTIFICACION)
                .nombre(NOMBRE)
                .telefono(TELEFONO)
                .email(EMAIL_VALIDO)
                .direccion(DIRECCION)
                .activo(true)
                .build();
        Assertions.assertNotNull(cliente);
    }

    @Test
    @DisplayName("Cliente sin identificación")
    void testClienteSinIdentificacion() {
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            Cliente cliente = Cliente.builder()
                    .nombre(NOMBRE)
                    .telefono(TELEFONO)
                    .email(EMAIL_VALIDO)
                    .direccion(DIRECCION)
                    .activo(true)
                    .build();
        });
    }

    @Test
    @DisplayName("Cliente sin nombre")
    void testClienteSinNombre() {
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            Cliente cliente = Cliente.builder()
                    .numeroIdentificacion(IDENTIFICACION)
                    .telefono(TELEFONO)
                    .email(EMAIL_VALIDO)
                    .direccion(DIRECCION)
                    .activo(true)
                    .build();
        });
    }

    @Test
    @DisplayName("Cliente sin teléfono")
    void testClienteSinTelefono() {
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            Cliente cliente = Cliente.builder()
                    .numeroIdentificacion(IDENTIFICACION)
                    .nombre(NOMBRE)
                    .email(EMAIL_VALIDO)
                    .direccion(DIRECCION)
                    .activo(true)
                    .build();
        });
    }

    @Test
    @DisplayName("Cliente sin email")
    void testClienteSinEmail() {
        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            Cliente cliente = Cliente.builder()
                    .numeroIdentificacion(IDENTIFICACION)
                    .nombre(NOMBRE)
                    .telefono(TELEFONO)
                    .direccion(DIRECCION)
                    .activo(true)
                    .build();
        });
    }

    @Test
    @DisplayName("Cliente con email inválido")
    void testClienteEmailInvalido() {
        Assertions.assertThrows(ExcepcionEmail.class, () -> {
            Cliente cliente = Cliente.builder()
                    .numeroIdentificacion(IDENTIFICACION)
                    .nombre(NOMBRE)
                    .telefono(TELEFONO)
                    .email(EMAIL_INVALIDO)
                    .direccion(DIRECCION)
                    .activo(true)
                    .build();
        });
    }
}

