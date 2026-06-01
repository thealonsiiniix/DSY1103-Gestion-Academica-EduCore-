package cl.instituto.pacifico.ms_estudiantes.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(
        name = "Estudiante",
        description = "Representa un estudiante registrado en el Instituto Pacífico"
)
@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
            title = "Identificador único del estudiante",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "El rut es obligatorio")
    @Schema(
            description = "RUT del estudiante",
            example = "20.123.456-7"
    )
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(
            description = "Nombre completo del estudiante",
            example = "Juan Pérez"
    )
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Schema(
            description = "Correo electrónico institucional o personal",
            example = "juan.perez@gmail.com"
    )
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Schema(
            description = "Número telefónico de contacto",
            example = "+56912345678"
    )
    private String telefono;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Schema(
            description = "Fecha de nacimiento del estudiante",
            example = "2002-05-15"
    )
    private LocalDate fechaNacimiento;

    @Schema(
            description = "Fecha de registro en el sistema",
            example = "2026-06-01"
    )
    private LocalDate fechaRegistro;

    public Estudiante() {
    }

    public Estudiante(Long id, String rut, String nombre, String email, String telefono, LocalDate fechaNacimiento, LocalDate fechaRegistro) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}