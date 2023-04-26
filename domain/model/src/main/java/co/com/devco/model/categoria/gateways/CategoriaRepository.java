package co.com.devco.model.categoria.gateways;

import co.com.devco.model.categoria.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Optional<Categoria> obtenerCategoria(Long idCategoria);
    List<Categoria> obtenerCategorias();
}
