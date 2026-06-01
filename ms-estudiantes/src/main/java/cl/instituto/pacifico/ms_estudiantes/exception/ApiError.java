package cl.instituto.pacifico.ms_estudiantes.exception;
import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String> details;

    public ApiError(int status, String error, String message, String path, List<String> details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }

    public int getStatus() {
        return status; }

    public String getError() {
        return error; }

    public String getMessage() {
        return message; }

    public String getPath() {
        return path; }

    public LocalDateTime getTimestamp() {
        return timestamp; }

    public List<String> getDetails() {
        return details; }
}