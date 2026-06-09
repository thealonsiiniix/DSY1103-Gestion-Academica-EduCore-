package cl.instituto.pacifico.ms_finanzas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "convenios_pago")

@Schema(
        description = "Entidad que representa un convenio de pago para un estudiante"
)
public class ConvenioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
            description = "Identificador único del convenio",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Cantidad de cuotas del convenio",
            example = "12"
    )
    private Integer cuotas;

    @Column(name = "monto_total")

    @Schema(
            description = "Monto total a pagar en el convenio",
            example = "1200000"
    )
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