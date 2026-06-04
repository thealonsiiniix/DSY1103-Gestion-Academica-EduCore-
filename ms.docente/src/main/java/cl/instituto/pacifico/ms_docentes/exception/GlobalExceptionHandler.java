package cl.instituto.pacifico.ms_docentes.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> notFound(
            ResourceNotFoundException ex,
            HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, "Not Found", ex.getMessage(), req.getRequestURI(), null
                ));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> business(
            BusinessException ex,
            HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ApiError(
                        422,
                        "Unprocessable Entity",
                        ex.getMessage(),
                        req.getRequestURI(),
                        null
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " +
                                error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest()
                .body(new ApiError(
                        400,
                        "Bad Request",
                        "Errores de validación",
                        req.getRequestURI(),
                        errors
                ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> constraint(
            ConstraintViolationException ex,
            HttpServletRequest req) {

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(error ->
                        error.getPropertyPath() + ": " +
                                error.getMessage())
                .toList();

        return ResponseEntity.badRequest()
                .body(new ApiError(
                        400,
                        "Bad Request",
                        "Restricción violada",
                        req.getRequestURI(),
                        errors
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> generic(
            Exception ex,
            HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(
                        500,
                        "Internal Server Error",
                        ex.getMessage(),
                        req.getRequestURI(),
                        null
                ));
    }
}