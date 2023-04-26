package co.com.devco.api.exception;

import co.com.devco.api.dto.RespuestaError;
import co.com.devco.usecase.exception.ExcepcionDuplicidad;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidacionesExceptionHandler {

    private static final String CAMPOS_INVALIDOS = "Error de validacion en campos";
    private static final String DATOS_DUPLICADOS = "Datos duplicados";
    private static final String DATOS_NO_EXISTEN = "Datos no existen";



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaError> manejarArgumentoNoValido(MethodArgumentNotValidException ex){

        List<String> campos = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String mensajeError = error.getDefaultMessage();
            campos.add(error.getField() + " (" + mensajeError + ")");
        }

        RespuestaError respuestaError = RespuestaError.builder()
                .mensaje(CAMPOS_INVALIDOS)
                .detalles(campos)
                .build();

        return ResponseEntity.badRequest().body(respuestaError);
    }

    @ExceptionHandler(ExcepcionDuplicidad.class)
    public ResponseEntity<RespuestaError> manejarDuplicidad(ExcepcionDuplicidad ex){
        String error = ex.getMessage();
        RespuestaError respuestaError = RespuestaError.builder()
                .mensaje(DATOS_DUPLICADOS)
                .detalles(error)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuestaError);
    }

    @ExceptionHandler(ExcepcionNoEncontrado.class)
    public ResponseEntity<RespuestaError> manejarNoEncontrado(ExcepcionNoEncontrado ex){
        String error = ex.getMessage();
        RespuestaError respuestaError = RespuestaError.builder()
                .mensaje(DATOS_NO_EXISTEN)
                .detalles(error)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaError);
    }
}
