package cl.instituto.pacifico.ms_empresas.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String rut;
    private String direccion;
    private String telefono;
    private String email;
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
