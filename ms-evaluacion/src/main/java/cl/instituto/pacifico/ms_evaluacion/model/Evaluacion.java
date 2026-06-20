package cl.instituto.pacifico.ms_evaluacion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
            description = "Identificador único de la evaluación",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "ID de la matrícula asociada a la evaluación",
            example = "10"
    )
    private Long matriculaId;

    @NotNull(message = "La nota no puede ser null")
    @Min(value = 1, message = "La nota no puede ser menor a 1")
    @Max(value = 7, message = "La nota no puede ser mayor a 7")

    @Schema(
            description = "Nota obtenida por el estudiante",
            example = "6.5"
    )
    private Double nota;

    public Evaluacion() {
    }

    public Evaluacion(Long id, Long matriculaId, Double nota) {
        this.id = id;
        this.matriculaId = matriculaId;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public Double getNota() {
        return nota;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}