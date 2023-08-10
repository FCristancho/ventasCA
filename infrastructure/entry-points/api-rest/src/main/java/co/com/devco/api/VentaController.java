package co.com.devco.api;

import co.com.devco.model.venta.Venta;
import co.com.devco.usecase.dto.VentaDto;
import co.com.devco.usecase.venta.VentaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ventas", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VentaController {

    private VentaUseCase useCase;

    @GetMapping
    public List<VentaDto> obtenerVentas(){
        return this.useCase.obtenerVentas();
    }

    @GetMapping("/{id}")
    public VentaDto obtenerVentaPorId(@PathVariable Long id){
        return this.useCase.obtenerVentaPorId(id);
    }
}
