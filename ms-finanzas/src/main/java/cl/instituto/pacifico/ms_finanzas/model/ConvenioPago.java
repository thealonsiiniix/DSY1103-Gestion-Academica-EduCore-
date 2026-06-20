package cl.instituto.pacifico.ms_finanzas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

    @NotNull(message = "La cantidad de cuotas es obligatoria")
    @Min(value = 1, message = "Debe existir al menos una cuota")
    @Schema(
            description = "Cantidad de cuotas del convenio",
            example = "12"
    )
    private Integer cuotas;

    @Column(name = "monto_total")

    @NotNull(message = "El monto total es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
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

    public Integer getCuotas() {
        return cuotas;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
}