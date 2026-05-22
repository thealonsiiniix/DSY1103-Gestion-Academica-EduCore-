package cl.instituto.pacifico.ms_asistencia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;           // ID de la asistencia
    private Long estudianteId;
    private String rutEstudiante;
    private String nombreEstudiante;
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
