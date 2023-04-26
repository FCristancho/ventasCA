package co.com.devco.jpa.repository.cliente;

import co.com.devco.jpa.entities.ClienteDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ClienteRepository extends CrudRepository<ClienteDB, Long>, QueryByExampleExecutor<ClienteDB> {

    boolean existsByNumeroIdentificacion(Long numeroIdentificacion);
}
