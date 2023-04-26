package co.com.devco.usecase.cliente;

import co.com.devco.model.cliente.Cliente;
import co.com.devco.model.cliente.gateways.ClienteRepository;
import co.com.devco.usecase.exception.ExcepcionDuplicidad;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteUseCaseTest {

    @Mock
    ClienteRepository repository;

    @InjectMocks
    ClienteUseCase clienteUseCase;

    @Test
    void testObtenerClienteExitosamente() {
        when(repository.obtenerCliente(anyLong())).thenReturn(Optional.of(Datos.CLIENTE));

        Cliente cliente = clienteUseCase.obtenerCliente(1L);

        assertEquals(Datos.CLIENTE, cliente);
        assertEquals(1L, cliente.getId());
        assertEquals("Felipe", cliente.getNombre());
    }

    @Test
    void testObtenerClienteLanzaExcepcion() {
        when(repository.obtenerCliente(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ExcepcionNoEncontrado.class, () -> {
            clienteUseCase.obtenerCliente(1L);
        });

        assertEquals(ExcepcionNoEncontrado.class, exception.getClass());
        verify(repository).obtenerCliente(1L);
    }

    @Test
    void testObtenerClientesExitosamente() {
        when(repository.obtenerClientes()).thenReturn(Datos.CLIENTES);

        List<Cliente> clientes = clienteUseCase.obtenerClientes();

        assertEquals(2, clientes.size());

    }

    @Test
    void testCrearClienteExitosamente() {
        Cliente clienteNuevo = Datos.CLIENTE_NUEVO;

        when(repository.existeIdentificacion(anyLong())).thenReturn(false);
        when(repository.guardarCliente(any(Cliente.class))).then(new Answer<Object>() {

            Long secuencia = 1L;
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Cliente cliente = invocation.getArgument(0);
                cliente.setId(secuencia++);
                return cliente;
            }
        });

        Cliente clienteCreado = clienteUseCase.crearCliente(clienteNuevo);

        assertNotNull(clienteCreado.getId());
        assertEquals(1L, clienteCreado.getId());
        assertEquals("Nuevo", clienteCreado.getNombre());

        verify(repository).existeIdentificacion(anyLong());
        verify(repository).guardarCliente(any(Cliente.class));
    }

    @Test
    void testCrearClienteConIdentificacionExistente() {
        Cliente clienteNuevo = Datos.CLIENTE_NUEVO;
        when(repository.existeIdentificacion(anyLong())).thenReturn(true);

        Exception exception = assertThrows(ExcepcionDuplicidad.class, () -> {
            clienteUseCase.crearCliente(clienteNuevo);
        });

        assertEquals(ExcepcionDuplicidad.class, exception.getClass());
        verify(repository).existeIdentificacion(anyLong());
        verify(repository, never()).guardarCliente(any(Cliente.class));
    }

    @Test
    void testModificarClienteExitosamente() {
        Cliente clienteActualizado = Datos.CLIENTE_MODIFICADO;
        when(repository.obtenerCliente(anyLong())).thenReturn(Optional.of(Datos.CLIENTE));
        when(repository.guardarCliente(any(Cliente.class))).thenReturn(clienteActualizado);


        Cliente clienteActual = clienteUseCase.modificarCliente(clienteActualizado);

        assertEquals("Daniel", clienteActual.getNombre());

        verify(repository).obtenerCliente(anyLong());
        verify(repository).guardarCliente(any(Cliente.class));
    }

    @Test
    void testModificarClienteInexistente() {
        Cliente clienteActualizado = Datos.CLIENTE_MODIFICADO;
        when(repository.obtenerCliente(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ExcepcionNoEncontrado.class, () -> {
            clienteUseCase.modificarCliente(clienteActualizado);
        });

        assertEquals(ExcepcionNoEncontrado.class, exception.getClass());
        verify(repository).obtenerCliente(1L);
        verify(repository, never()).guardarCliente(any(Cliente.class));
    }
}