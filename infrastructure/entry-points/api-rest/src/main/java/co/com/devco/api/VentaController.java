package co.com.devco.api;

import co.com.devco.model.venta.Venta;
import co.com.devco.usecase.cliente.ClienteUseCase;
import co.com.devco.usecase.dto.VentaDto;
import co.com.devco.usecase.dto.VentaNuevaDto;
import co.com.devco.usecase.venta.VentaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ventas", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VentaController {

    private VentaUseCase ventaUseCase;
    private ClienteUseCase clienteUseCase;

    @GetMapping
    public List<VentaDto> obtenerVentas(){
        return this.ventaUseCase.obtenerVentas();
    }

    @GetMapping("/{id}")
    public VentaDto obtenerVentaPorId(@PathVariable Long id){
        return this.ventaUseCase.obtenerVentaPorId(id);
    }

    @GetMapping("/cliente")
    public List<VentaDto> obtenerVentasPorCliente(@RequestParam Long identificacion){
        return this.clienteUseCase.obtenerVentasPorCliente(identificacion);
    }

    @PostMapping
    public VentaDto guardarVenta(@RequestBody VentaNuevaDto venta){
        return this.ventaUseCase.guardarVenta(venta);
    }
}
