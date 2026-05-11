package cl.instituto.pacifico.ms_finanzas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aranceles")
public class Arancel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estudianteRut;
    private String nombre;
    private Double monto;
    private String fecha;
    private String estado;

    public Arancel() {}

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getEstudianteRut() {
        return estudianteRut;
    }
    public void setEstudianteRut(String estudianteRut) {
        this.estudianteRut = estudianteRut;
    }
}