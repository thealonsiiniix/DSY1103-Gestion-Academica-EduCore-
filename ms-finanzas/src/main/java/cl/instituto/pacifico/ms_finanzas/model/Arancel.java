package cl.instituto.pacifico.ms_finanzas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "aranceles")

@Schema(
        description = "Entidad que representa un arancel asociado a un estudiante"
)
public class Arancel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
            description = "Identificador único del arancel",
            example = "1"
    )
    private Long id;

    @Column(name = "estudiante_rut")

    @Schema(
            description = "RUT del estudiante asociado al arancel",
            example = "12345678-9"
    )
    private String estudianteRut;

    @Schema(
            description = "Nombre del estudiante",
            example = "Juan Pérez"
    )
    private String nombre;

    @Schema(
            description = "Monto total del arancel",
            example = "1500000"
    )
    private Double monto;

    @Schema(
            description = "Fecha de registro del arancel",
            example = "2026-06-15"
    )
    private String fecha;

    @Schema(
            description = "Estado del arancel",
            example = "PENDIENTE"
    )
    private String estado;

    public Arancel() {
    }

    public Arancel(Long id, String estudianteRut, String nombre,
                   Double monto, String fecha, String estado) {

        this.id = id;
        this.estudianteRut = estudianteRut;
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstudianteRut() {
        return estudianteRut;
    }

    public void setEstudianteRut(String estudianteRut) {
        this.estudianteRut = estudianteRut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}