package co.com.devco.model.utils;

import co.com.devco.model.exception.ExcepcionEmail;
import co.com.devco.model.exception.ExcepcionValorInvalido;
import co.com.devco.model.exception.ExcepcionValorObligatorio;

public class ValidadorArgumento {

    private ValidadorArgumento() {}

    public static void validarObligatorio(Object valor, String mensaje){
        if(valor == null)
            throw new ExcepcionValorObligatorio(mensaje);
    }

    public static void validarCadenasTexto(String valor, String mensaje){
       if(valor.isBlank())
           throw new ExcepcionValorInvalido(mensaje);
    }

    public static void validarEmail(String email, String mensaje){
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if(!email.matches(regex))
            throw new ExcepcionEmail(mensaje);
    }
}
