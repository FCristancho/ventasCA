package co.com.devco.jpa.repository.producto;

import co.com.devco.jpa.entities.ProductoDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ProductoRepository extends CrudRepository<ProductoDB, Long>, QueryByExampleExecutor<ProductoDB> {
}
