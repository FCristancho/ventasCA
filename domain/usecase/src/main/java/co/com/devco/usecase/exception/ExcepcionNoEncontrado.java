package co.com.devco.usecase.exception;

public class ExcepcionNoEncontrado extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
