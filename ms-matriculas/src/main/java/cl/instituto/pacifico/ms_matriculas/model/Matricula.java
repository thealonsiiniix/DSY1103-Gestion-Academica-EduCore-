package cl.instituto.pacifico.ms_matriculas.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
@Schema(
        name = "Matricula",
        description = "Representa la matrícula de un estudiante en una carrera académica"
)
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            title = "Identificador único de la matrícula",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotNull(message = "El ID del estudiante es obligatorio")
    @Column(name = "estudiante_id", nullable = false)
    @Schema(
            description = "Identificador del estudiante matriculado",
            example = "1"
    )
    private Long estudianteId;

    @NotNull(message = "El ID de carrera es obligatorio")
    @Column(name = "carrera_id", nullable = false)
    @Schema(
            description = "Identificador de la carrera seleccionada",
            example = "3"
    )
    private Long carreraId;

    @NotBlank(message = "La sección es obligatoria")
    @Column(name = "seccion", nullable = false, length = 50)
    @Schema(
            description = "Sección asignada al estudiante",
            example = "A-101"
    )
    private String seccion;

    @Column(name = "fecha_matricula", updatable = false)
    @Schema(
            description = "Fecha en que se registró la matrícula",
            example = "2026-06-02",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDate fechaMatricula;

    @Column(name = "estado", length = 20)
    @Schema(
            description = "Estado actual de la matrícula",
            example = "ACTIVA",
            allowableValues = {
                    "ACTIVA",
                    "SUSPENDIDA",
                    "FINALIZADA"
            }
    )
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
