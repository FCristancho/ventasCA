package co.com.devco.api;

import co.com.devco.model.producto.Producto;
import co.com.devco.model.producto.gateways.ProductoRepository;
import co.com.devco.usecase.producto.ProductoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/productos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProductoController {

    private final ProductoUseCase useCase;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> productos = useCase.obtenerProductos();
        return ResponseEntity.ok(productos);
    }
}
