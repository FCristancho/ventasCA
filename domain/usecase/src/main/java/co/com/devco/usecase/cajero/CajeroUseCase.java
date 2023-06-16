package co.com.devco.usecase.cajero;

import co.com.devco.model.cajero.Cajero;
import co.com.devco.model.cajero.gateways.CajeroRepository;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static co.com.devco.usecase.utils.Messages.CAJERO_NO_ENCONTRADO;

@RequiredArgsConstructor
public class CajeroUseCase {

    private final CajeroRepository cajeroRepository;

    public Cajero obtenerCajero(Long id){
        return this.cajeroRepository.obtenerCajero(id).orElseThrow(
                () -> new ExcepcionNoEncontrado(CAJERO_NO_ENCONTRADO)
        );
    }

    public List<Cajero> obtenerCajeros(){
        return this.cajeroRepository.obtenerCajeros();
    }
}
