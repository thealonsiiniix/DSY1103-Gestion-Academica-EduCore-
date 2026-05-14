package cl.instituto.pacifico.ms_practicas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Practica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long estudianteId;
    private String rutEstudiante;
    private String nombreEstudiante;
    private String emailEstudiante;
    private String telefonoEstudiante;
    private Long idEmpresa;
    private String nombreEmpresa;
    private String rutEmpresa;
    private String direccionEmpresa;
    private String telefonoEmpresa;
    private String emailEmpresa;
    private Boolean convenioVigenteEmpresa;
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
