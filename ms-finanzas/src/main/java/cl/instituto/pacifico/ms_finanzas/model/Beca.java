package cl.instituto.pacifico.ms_finanzas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "becas")

@Schema(
        description = "Entidad que representa una beca otorgada a un estudiante"
)
public class Beca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
            description = "Identificador único de la beca",
            example = "1"
    )
    private Long id;

    @NotBlank(message = "El nombre de la beca es obligatorio")
    @Schema(
            description = "Nombre de la beca",
            example = "Beca Excelencia Académica"
    )
    private String nombre;

    @NotNull(message = "El porcentaje es obligatorio")
    @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
    @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
    @Schema(
            description = "Porcentaje de descuento aplicado",
            example = "50.0"
    )
    private Double porcentaje;

    public Beca() {
    }

    public Beca(Long id, String nombre, Double porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}