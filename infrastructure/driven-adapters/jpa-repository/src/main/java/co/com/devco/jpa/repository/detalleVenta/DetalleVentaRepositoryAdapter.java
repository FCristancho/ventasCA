package co.com.devco.jpa.repository.detalleVenta;

import co.com.devco.jpa.entities.DetalleVentaDB;
import co.com.devco.jpa.entities.DetalleVentaPK;
import co.com.devco.jpa.helper.AdapterOperations;
import co.com.devco.model.venta.DetalleVenta;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleVentaRepositoryAdapter extends AdapterOperations<DetalleVenta, DetalleVentaDB, DetalleVentaPK, DetalleVentaRepository>
    implements co.com.devco.model.venta.gateways.DetalleVentaRepository{

    public DetalleVentaRepositoryAdapter(DetalleVentaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, DetalleVenta.class));
    }

    @Override
    public DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta) {
        System.out.println(detalleVenta);
        return save(detalleVenta);
    }
}
