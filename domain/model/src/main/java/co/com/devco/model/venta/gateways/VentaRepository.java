package co.com.devco.model.venta.gateways;

import co.com.devco.model.venta.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaRepository {

    Venta guardarVenta();
    Optional<Venta> obtenerVenta(Long idVenta);
    List<Venta> obtenerVentas();

}
