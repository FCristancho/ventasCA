package co.com.devco.jpa.repository.categoria;

import co.com.devco.jpa.entities.CategoriaDB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CategoriaRepository extends CrudRepository<CategoriaDB, Long>, QueryByExampleExecutor<CategoriaDB> {

}
