package co.com.devco.model.producto;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static co.com.devco.model.utils.Messages.STOCK_NO_DISPONIBLE;
import static co.com.devco.model.utils.ValidadorArgumento.validarPositivo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Producto {


    private Long id;
    private String nombre;
    private double precio;
    private int stock;

    public void descontarStock(int cantidad){
        int stock = this.stock - cantidad;
        validarPositivo(stock, STOCK_NO_DISPONIBLE);
        this.stock = stock;
    }

    public void devolverStock(int cantidad){
        this.stock += cantidad;
    }
}
