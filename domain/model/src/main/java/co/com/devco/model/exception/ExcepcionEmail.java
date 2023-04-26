package co.com.devco.model.exception;

public class ExcepcionEmail extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionEmail(String mensaje) {
        super(mensaje);
    }
}
