package co.com.devco.jpa.repository.venta;

import co.com.devco.jpa.entities.VentaDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface VentaRepository extends CrudRepository<VentaDB, Long>, QueryByExampleExecutor<VentaDB> {
}
