package cl.instituto.pacifico.ms_titulacion.model;

public class Titulacion {
    private Long id;
    private Long evaluacionId;
    private String fecha;
    private String estado;

    public Titulacion() {
    }

    public Titulacion(Long id, Long evaluacionId, String fecha, String estado) {
        this.id = id;
        this.evaluacionId = evaluacionId;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Long evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
