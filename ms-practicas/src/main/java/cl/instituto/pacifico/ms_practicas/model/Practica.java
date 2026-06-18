package cl.instituto.pacifico.ms_practicas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Schema(
        name = "Practica",
        description = "Representa una practica registrado en el Instituto Pacífico"
)

@Entity
public class Practica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            title = "Identificador único de la practica",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;
    @Schema(
            description = "Identificador del estudiante",
            example = "1"
    )
    private Long estudianteId;
    @Schema(
            description = "Identificador del rut del estudiante",
            example = "111223330"
    )
    private String rutEstudiante;
    @Schema(
            description = "Nombre del estudiante",
            example = "Juan Perez"
    )
    private String nombreEstudiante;

    @Schema(
            description = "Email del estudiante",
            example = "juan@gmail.com"
    )
    private String emailEstudiante;
    @Schema(
            description = "Telefono del estudiante",
            example = "945105119"
    )
    private String telefonoEstudiante;
    @Schema(
            description = "Identificador de la empresa",
            example = "1"
    )
    private Long idEmpresa;
    @Schema(
            description = "Nombre de la empresa",
            example = "Juan Perez"
    )
    private String nombreEmpresa;
    @Schema(
            description = "Identificador del rut de la empresa",
            example = "123456789"
    )
    private String rutEmpresa;
    @Schema(
            description = "direccion del estudiante",
            example = "Egaña 500"
    )
    private String direccionEmpresa;
    @Schema(
            description = "Telefono de la empresa",
            example = "300500000"
    )
    private String telefonoEmpresa;
    @Schema(
            description = "Email de la empresa",
            example = "FixTech@gmail.com"
    )
    private String emailEmpresa;
    @Schema(
            description = "Convenio vigente con la institucion",
            example = "true",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Boolean convenioVigenteEmpresa;
    @Schema(
            description = "Estado del arancel",
            example = "pagado"
    )
    private String estadoArancel;

    public Practica() {
    }

    public Practica(Long id, Long estudianteId, String rutEstudiante, String nombreEstudiante, String emailEstudiante, String telefonoEstudiante, Long idEmpresa, String nombreEmpresa, String rutEmpresa, String direccionEmpresa, String telefonoEmpresa, String emailEmpresa, Boolean convenioVigenteEmpresa, String estadoArancel, String estudianteRut) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.rutEstudiante = rutEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.emailEstudiante = emailEstudiante;
        this.telefonoEstudiante = telefonoEstudiante;
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.rutEmpresa = rutEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.emailEmpresa = emailEmpresa;
        this.convenioVigenteEmpresa = convenioVigenteEmpresa;
        this.estadoArancel = estadoArancel;
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
    public String getEmailEstudiante() {
        return emailEstudiante;
    }
    public void setEmailEstudiante(String emailEstudiante) {
        this.emailEstudiante = emailEstudiante;
    }
    public String getTelefonoEstudiante() {
        return telefonoEstudiante;
    }
    public void setTelefonoEstudiante(String telefonoEstudiante) {
        this.telefonoEstudiante = telefonoEstudiante;
    }
    public Long getIdEmpresa() {
        return idEmpresa;
    }
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    public String getRutEmpresa() {
        return rutEmpresa;
    }
    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }
    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }
    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }
    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }
    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }
    public String getEmailEmpresa() {
        return emailEmpresa;
    }
    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }
    public Boolean getConvenioVigenteEmpresa() {
        return convenioVigenteEmpresa;
    }
    public void setConvenioVigenteEmpresa(Boolean convenioVigenteEmpresa) {
        this.convenioVigenteEmpresa = convenioVigenteEmpresa;
    }
    public String getEstadoArancel() {
        return estadoArancel;
    }
    public void setEstadoArancel(String estadoArancel) {
        this.estadoArancel = estadoArancel;
    }
}
