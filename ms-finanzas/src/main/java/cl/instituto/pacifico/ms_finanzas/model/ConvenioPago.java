package cl.instituto.pacifico.ms_finanzas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "convenios_pago")
public class ConvenioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cuotas;

    @Column(name = "monto_total")
    private Double montoTotal;

    public ConvenioPago() {
    }

    public ConvenioPago(Long id, Integer cuotas, Double montoTotal) {
        this.id = id;
        this.cuotas = cuotas;
        this.montoTotal = montoTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
}