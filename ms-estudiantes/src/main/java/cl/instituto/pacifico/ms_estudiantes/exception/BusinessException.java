package cl.instituto.pacifico.ms_estudiantes.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}