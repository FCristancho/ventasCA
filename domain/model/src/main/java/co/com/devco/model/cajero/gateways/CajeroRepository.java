package co.com.devco.model.cajero.gateways;

import co.com.devco.model.cajero.Cajero;

import java.util.List;
import java.util.Optional;

public interface CajeroRepository {

    Optional<Cajero> obtenerCajero(Long id);
    List<Cajero> obtenerCajeros();
}
