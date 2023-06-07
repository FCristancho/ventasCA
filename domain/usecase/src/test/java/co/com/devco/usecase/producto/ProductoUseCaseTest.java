package co.com.devco.usecase.producto;

import co.com.devco.model.producto.Producto;
import co.com.devco.model.producto.gateways.ProductoRepository;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductoUseCaseTest {

    @Mock
    ProductoRepository productoRepository;

    @InjectMocks
    ProductoUseCase productoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerProductoExitosamente() {
        Producto producto = new Producto(1L, "Producto 1", 10.0, 10);
        when(productoRepository.obtenerProducto(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoUseCase.obtenerProducto(1L);

        assertEquals("Producto 1", resultado.getNombre());
    }

    @Test
    void obtenerProducto_NonExistingId_ExceptionThrown() {
        when(productoRepository.obtenerProducto(1L)).thenReturn(Optional.empty());

        assertThrows(ExcepcionNoEncontrado.class, () -> {
            productoUseCase.obtenerProducto(1L);
        });
    }

    @Test
    void obtenerProductos_ProductosReturned() {
        List<Producto> productos = Arrays.asList(
                new Producto(1L, "Producto 1", 10.0, 10),
                new Producto(2L, "Producto 2", 20.0, 20)
        );
        when(productoRepository.obtenerProductos()).thenReturn(productos);

        List<Producto> resultado = productoUseCase.obtenerProductos();

        assertEquals(2, resultado.size());
    }

    @Test
    void actualizarStock_DescontarStock_StockUpdated() {
        Long idProducto = 1L;
        int cantidad = 5;
        boolean descontar = true;
        Producto producto = new Producto(1L, "Producto 1", 10.0, 10);
        when(productoRepository.obtenerProducto(anyLong())).thenReturn(Optional.of(producto));
        when(productoRepository.guardarProducto(ArgumentMatchers.any(Producto.class))).thenReturn(producto);

        Producto resultado = productoUseCase.actualizarStock(idProducto, cantidad, descontar);

        verify(productoRepository).guardarProducto(resultado);
        assertEquals(5, resultado.getStock());
    }

    @Test
    void actualizarStock_DevolverStock_StockUpdated() {
        Long idProducto = 1L;
        int cantidad = 5;
        boolean descontar = false;
        Producto producto = new Producto(1L, "Producto 1", 10.0, 10);
        when(productoRepository.obtenerProducto(anyLong())).thenReturn(Optional.of(producto));
        when(productoRepository.guardarProducto(ArgumentMatchers.any(Producto.class))).thenReturn(producto);

        Producto resultado = productoUseCase.actualizarStock(idProducto, cantidad, descontar);

        verify(productoRepository).guardarProducto(resultado);
        assertEquals(15, resultado.getStock());
    }
}


