package cl.instituto.pacifico.ms_docentes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "docentes")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
            description = "Identificador único del docente",
            example = "1"
    )
    private Long id;

    @NotBlank(message = "El rut es obligatorio")
    @Schema(
            description = "RUT del docente",
            example = "12345678-9"
    )
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(
            description = "Nombre del docente",
            example = "Juan"
    )
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Schema(
            description = "Apellido del docente",
            example = "Pérez"
    )
    private String apellido;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    @Schema(
            description = "Correo electrónico institucional",
            example = "juan.perez@instituto.cl"
    )
    private String correo;

    public Docente() {
    }

    public Docente(Long id, String rut, String nombre, String apellido, String correo) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}