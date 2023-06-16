package co.com.devco.jpa.repository.cajero;

import co.com.devco.jpa.entities.CajeroDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CajeroRepository extends CrudRepository<CajeroDB, Long>, QueryByExampleExecutor<CajeroDB> {

}
