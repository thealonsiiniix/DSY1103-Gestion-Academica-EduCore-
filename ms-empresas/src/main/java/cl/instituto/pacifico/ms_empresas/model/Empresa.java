package cl.instituto.pacifico.ms_empresas.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(
        name = "Empresa",
        description = "Representa una empresa asociada al Instituto Pacífico"
)
@Entity
public class Empresa {
    @Schema(
            title = "ID de empresa",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            description = "Nombre de la empresa",
            example = "Banco Estado"
    )
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(
            description = "RUT de la empresa",
            example = "76.123.456-7"
    )
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @Schema(
            description = "Dirección de la empresa",
            example = "Av. Los Carrera 123"
    )
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    @Schema(
            description = "Teléfono de contacto",
            example = "+56912345678"
    )
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @Schema(
            description = "Correo electrónico",
            example = "contacto@empresa.cl"
    )
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Schema(
            description = "Indica si existe convenio vigente",
            example = "true"
    )
    @NotNull(message = "El convenio es obligatorio")
    private Boolean convenioVigente;

    // Constructor vacío
    public Empresa() {
    }
    // Constructor con datos
    public Empresa(Long id, String nombre, String rut, String direccion, String telefono, String email, Boolean convenioVigente) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.convenioVigente = convenioVigente;
    }
    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getConvenioVigente() {
        return convenioVigente;
    }

    public void setConvenioVigente(Boolean convenioVigente) {
        this.convenioVigente = convenioVigente;
    }
}
