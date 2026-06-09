package cl.instituto.pacifico.ms_finanzas.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}