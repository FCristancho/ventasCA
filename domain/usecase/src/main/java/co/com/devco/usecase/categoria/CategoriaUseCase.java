package co.com.devco.usecase.categoria;

import co.com.devco.model.categoria.Categoria;
import co.com.devco.model.categoria.gateways.CategoriaRepository;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoriaUseCase {

    private static final String CATEGORIA_NO_ENCONTRADA = "Categoria no encontrada";
    private final CategoriaRepository categoriaGateway;

    public Categoria obtenerCategoria(Long id){
        return this.categoriaGateway.obtenerCategoria(id).orElseThrow(
                () -> new ExcepcionNoEncontrado(CATEGORIA_NO_ENCONTRADA)
        );
    }

    public List<Categoria> obtenerCategorias(){
        return this.categoriaGateway.obtenerCategorias();
    }
}
