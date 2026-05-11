package cl.instituto.pacifico.ms_evaluacion.model;

public class Evaluacion {
    private Long id;
    private Long matriculaId;
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
