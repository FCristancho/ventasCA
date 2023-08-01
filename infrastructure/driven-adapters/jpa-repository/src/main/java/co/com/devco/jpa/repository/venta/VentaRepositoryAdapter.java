package co.com.devco.jpa.repository.venta;

import co.com.devco.jpa.entities.VentaDB;
import co.com.devco.jpa.helper.AdapterOperations;
import co.com.devco.model.venta.Venta;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class VentaRepositoryAdapter extends AdapterOperations<Venta, VentaDB, Long, VentaRepository>
    implements co.com.devco.model.venta.gateways.VentaRepository {
    public VentaRepositoryAdapter(VentaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Venta.class));
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        return save(venta);
    }

    @Override
    public Optional<Venta> obtenerVenta(Long idVenta) {
        return Optional.ofNullable(findById(idVenta));
    }

    @Override
    public List<Venta> obtenerVentas() {
        return findAll();
    }
}
