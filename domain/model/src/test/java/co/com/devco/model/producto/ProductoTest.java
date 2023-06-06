package co.com.devco.model.producto;

import co.com.devco.model.exception.ExcepcionValorInvalido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto();
        producto.setStock(10);
    }

    @Test
    public void descontarStockExitosamente() {
        int cantidad = 5;
        producto.descontarStock(cantidad);
        Assertions.assertEquals(5, producto.getStock(), "El stock no se desconto correctamente");
    }

    @Test
    public void descontarStock_InvalidAmount_ExceptionThrown() {
        int cantidad = 15;
        Assertions.assertThrows(ExcepcionValorInvalido.class, () -> {
            producto.descontarStock(cantidad);
        }, "Se esperaba una excepción para una cantidad de stock inválida");
    }

    @Test
    public void devolverStock_ValidAmount_StockIncreases() {
        int cantidad = 5;
        producto.devolverStock(cantidad);
        Assertions.assertEquals(15, producto.getStock(), "El stock no se incrementó correctamente");
    }
}