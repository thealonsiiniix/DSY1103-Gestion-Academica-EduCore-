package cl.instituto.pacifico.ms_empresas.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}