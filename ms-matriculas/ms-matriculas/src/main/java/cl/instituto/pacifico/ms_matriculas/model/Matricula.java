package cl.instituto.pacifico.ms_matriculas.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long estudianteId; // referencia a ms-estudiantes
    private String carrera;
    private String seccion;
    private LocalDate fechaMatricula;
    private String estado; // ACTIVA, ANULADA

    //Constructor Vacio
    public Matricula() {
    }

    // Constructor
    public Matricula(Long id, Long estudianteId, String carrera, String seccion, LocalDate fechaMatricula, String estado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.carrera = carrera;
        this.seccion = seccion;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
