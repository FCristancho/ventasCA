package co.com.devco.usecase.cliente;

import co.com.devco.model.cliente.Cliente;

import java.util.Arrays;
import java.util.List;

public class Datos {

    public static final List<Cliente> CLIENTES = Arrays.asList(
            new Cliente(1L, 11111111L, "Felipe", "+57-11111111", "felipe@gmail.com", "Cra 4", true),
            new Cliente(2L, 22222222L, "Ivan", "+57-3201111111", "ivan@gmail.com", "Cra 5", true)
    );

    public static final Cliente CLIENTE =
            new Cliente(1L, 11111111L, "Felipe", "+57-22222222", "felipe@gmail.com", "Cra 4", true);

    public static final Cliente CLIENTE_NUEVO =
            new Cliente(null, 33333333L, "Nuevo", "+57-111111111", "felipe@gmail.com", "Cra 4", false);

    public static final Cliente CLIENTE_MODIFICADO =
            new Cliente(1L, 11111111L, "Daniel", "+57-32082762", "daniel@gmail.com", "Cra 8", false);

}
