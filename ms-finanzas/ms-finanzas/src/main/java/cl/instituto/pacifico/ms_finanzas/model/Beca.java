package cl.instituto.pacifico.ms_finanzas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "becas")
public class Beca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double porcentaje;

    public Beca() {}

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }
}