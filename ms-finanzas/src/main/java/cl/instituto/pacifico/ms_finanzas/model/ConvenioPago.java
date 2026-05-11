package cl.instituto.pacifico.ms_finanzas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "convenios_pago")
public class ConvenioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cuotas;
    private Double montoTotal;

    public ConvenioPago() {}

    public Long getId() { return id; }

    public Integer getCuotas() { return cuotas; }
    public void setCuotas(Integer cuotas) { this.cuotas = cuotas; }

    public Double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Double montoTotal) { this.montoTotal = montoTotal; }
}