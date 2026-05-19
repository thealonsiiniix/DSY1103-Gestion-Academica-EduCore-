package cl.instituto.pacifico.ms_matriculas.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;
    @NotNull(message = "El ID de carrera es obligatorio")
    private Long carreraId;
    @NotBlank(message = "La sección es obligatoria")
    private String seccion;
    private LocalDate fechaMatricula;
    private String estado;

    public Matricula() {
    }

    public Matricula(Long id, Long estudianteId, Long carreraId, String seccion, LocalDate fechaMatricula, String estado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.carreraId = carreraId;
        this.seccion = seccion;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

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

    public Long getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Long carreraId) {
        this.carreraId = carreraId;
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
