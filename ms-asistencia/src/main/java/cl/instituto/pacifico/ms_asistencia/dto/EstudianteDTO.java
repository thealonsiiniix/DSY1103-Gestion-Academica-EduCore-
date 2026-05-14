package cl.instituto.pacifico.ms_asistencia.dto;

// DTO contiene los datos que asistencia necesita de estudiante para funcionar
public class EstudianteDTO {
    public Long id;
    public String rut;
    public String nombre;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
