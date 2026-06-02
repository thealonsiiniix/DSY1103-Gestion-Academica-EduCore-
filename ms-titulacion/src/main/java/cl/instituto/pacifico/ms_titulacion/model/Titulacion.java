package cl.instituto.pacifico.ms_titulacion.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Schema(
        name = "Titulacion",
        description = "Representa el proceso de titulación de un estudiante"
)
public class Titulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotNull(message = "matriculaId obligatorio")
    @Schema(
            description = "ID de matrícula",
            example = "1"
    )
    private Long matriculaId;

    @NotNull(message = "fecha obligatoria")
    @Schema(
            description = "Fecha de titulación",
            example = "2026-06-02"
    )
    private LocalDate fecha;

    @NotBlank(message = "estado obligatorio")
    @Schema(
            description = "Estado de titulación",
            example = "TITULADO"
    )
    private String estado;

    public Titulacion() {
    }

    public Titulacion(Long id, Long matriculaId, LocalDate fecha, String estado) {
        this.id = id;
        this.matriculaId = matriculaId;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
