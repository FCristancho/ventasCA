package co.com.devco.usecase.categoria;

import co.com.devco.model.categoria.Categoria;
import co.com.devco.model.categoria.gateways.CategoriaRepository;
import co.com.devco.model.cliente.Cliente;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaUseCaseTest {

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    CategoriaUseCase categoriaUseCase;

    @Test
    void testObtenerCategoriasListaNoVacia() {
        when(repository.obtenerCategorias()).thenReturn(Datos.CATEGORIAS);

        List<Categoria> categorias = categoriaUseCase.obtenerCategorias();

        assertFalse(categorias.isEmpty());
        assertEquals(2, categorias.size());
    }

    @Test
    void testObtenerCategoriasListaVacia() {
        when(repository.obtenerCategorias()).thenReturn(Collections.EMPTY_LIST);

        List<Categoria> categorias = categoriaUseCase.obtenerCategorias();

        assertTrue(categorias.isEmpty());
    }

    @Test
    void testObtenerCategoriaExitosamente() {
        when(repository.obtenerCategoria(anyLong())).thenReturn(Optional.of(Datos.CATEGORIA));

        Categoria categoria = categoriaUseCase.obtenerCategoria(1L);

        assertEquals(Datos.CATEGORIA, categoria);
        assertEquals(1L, categoria.getId());
        assertEquals("Bebidas", categoria.getNombre());
    }

    @Test
    void testObtenerCategoriaLanzaExcepcion() {
        when(repository.obtenerCategoria(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ExcepcionNoEncontrado.class, () -> {
            categoriaUseCase.obtenerCategoria(1L);
        });

        assertEquals(ExcepcionNoEncontrado.class, exception.getClass());
        verify(repository).obtenerCategoria(1L);
    }
}