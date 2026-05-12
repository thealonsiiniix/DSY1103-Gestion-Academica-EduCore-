package cl.instituto.pacifico.ms_matriculas.model;
import java.time.LocalDate;

public class Matricula {
        private Long id;
        private Long estudianteId;
        private String carrera;
        private String seccion;
        private LocalDate fechaMatricula;
        private String estado;

        public Matricula() {
        }

    public Matricula(Long id, Long estudianteId, String carrera, String seccion, LocalDate fechaMatricula, String estado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.carrera = carrera;
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
