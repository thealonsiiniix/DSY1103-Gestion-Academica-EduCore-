package cl.instituto.pacifico.ms_titulacion.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}