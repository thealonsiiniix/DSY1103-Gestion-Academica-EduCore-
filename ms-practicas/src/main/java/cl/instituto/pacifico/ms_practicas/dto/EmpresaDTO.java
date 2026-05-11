package cl.instituto.pacifico.ms_practicas.dto;

public class EmpresaDTO {
    public Long id;
    public String nombre;
    public String rut;
    public String direccion;
    public String telefono;
    public String email;
    public Boolean convenioVigente;

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
