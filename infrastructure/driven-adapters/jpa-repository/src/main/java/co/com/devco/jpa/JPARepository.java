package co.com.devco.jpa;

import co.com.devco.jpa.entities.ClienteDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPARepository extends CrudRepository<ClienteDB, Long>, QueryByExampleExecutor<ClienteDB> {

    boolean existsByNumeroIdentificacion(Long numeroIdentificacion);
}
