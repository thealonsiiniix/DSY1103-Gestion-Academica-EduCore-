package cl.instituto.pacifico.ms_finanzas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

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

    @Schema(
            description = "Nombre de la beca",
            example = "Beca Excelencia Académica"
    )
    private String nombre;

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}