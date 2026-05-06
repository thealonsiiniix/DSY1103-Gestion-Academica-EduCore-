package cl.instituto.pacifico.ms_asistencia.model;

import java.time.LocalDate;

public class Asistencia {
    private Long id;           // ID de la asistencia
    private Long estudianteId;
    private String rut;
    private String estudiante;


    public Asistencia() {

    }

    public Asistencia(Long id, Long estudianteId, String rut, String estudiante) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.rut = rut;
        this.estudiante = estudiante;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }
}
