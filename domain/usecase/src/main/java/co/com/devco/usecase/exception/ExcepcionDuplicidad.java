package co.com.devco.usecase.exception;

public class ExcepcionDuplicidad extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionDuplicidad(String mensaje){
        super(mensaje);
    }
}
