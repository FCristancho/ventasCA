package co.com.devco.usecase.categoria;

import co.com.devco.model.categoria.Categoria;

import java.util.Arrays;
import java.util.List;

public class Datos {

    public static final List<Categoria> CATEGORIAS = Arrays.asList(
            new Categoria(1L, "Bebidas"),
            new Categoria(2L, "Carnes")
    );

    public static final Categoria CATEGORIA = new Categoria(1L, "Bebidas");
}
