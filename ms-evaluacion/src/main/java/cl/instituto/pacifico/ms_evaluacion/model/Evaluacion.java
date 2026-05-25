package cl.instituto.pacifico.ms_evaluacion.model;
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
    private Long id;
    private Long matriculaId;
    @NotNull(message = "La nota no puede ser null")
    @Min(value = 1, message = "La nota no puede ser menor a 1")
    @Max(value = 7, message = "La nota no puede ser mayor a 7")
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
