package co.com.devco.jpa.repository.detalleVenta;

import co.com.devco.jpa.entities.DetalleVentaDB;
import co.com.devco.jpa.entities.DetalleVentaPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface DetalleVentaRepository extends CrudRepository<DetalleVentaDB, DetalleVentaPK>, QueryByExampleExecutor<DetalleVentaDB> {
}
