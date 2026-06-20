package cl.instituto.pacifico.ms_asistencia.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
@Schema(
        name = "Asistencia",
        description = "Representa una asistencia registrado en el Instituto Pacífico"
)

@Entity
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            title = "Identificador único de la asistencia",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Identificador del estudiante",
            example = "1"
    )
    private Long estudianteId;

    @NotBlank(message = "El rut del estudiante es obligatorio")
    @Schema(
            description = "Identificador del rut del estudiante",
            example = "111223330"
    )
    private String rutEstudiante;

    @Schema(
            description = "Nombre del estudiante",
            example = "Juan Perez"
    )
    private String nombreEstudiante;

    @Schema(
            description = "Fecha en que se registró la asistencia",
            example = "2026-06-02",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDate fechaAsistencia;

    public Asistencia() {
    }

    public Asistencia(Long id, Long estudianteId, String rutEstudiante, String nombreEstudiante, LocalDate fechaAsistencia) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.rutEstudiante = rutEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.fechaAsistencia = fechaAsistencia;
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

    public String getRutEstudiante() {
        return rutEstudiante;
    }

    public void setRutEstudiante(String rutEstudiante) {
        this.rutEstudiante = rutEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public LocalDate getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDate fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }
}
