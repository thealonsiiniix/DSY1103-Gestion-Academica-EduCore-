package cl.instituto.pacifico.ms_titulacion.controller;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import cl.instituto.pacifico.ms_titulacion.service.TitulacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/titulaciones")
@Tag(
        name = "Titulaciones",
        description = "Operaciones relacionadas con el proceso de titulación"
)
public class TitulacionController {
    private final TitulacionService service;
    public TitulacionController(TitulacionService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear titulación",
            description = "Registra una nueva titulación validando requisitos académicos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Titulación creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "422", description = "Regla de negocio incumplida")
    })
    @PostMapping
    public ResponseEntity<Titulacion> crear(@Valid @RequestBody Titulacion titulacion) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(titulacion));
    }

    @Operation(
            summary = "Listar titulaciones",
            description = "Obtiene todas las titulaciones registradas"
    )
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    @GetMapping
    public ResponseEntity<List<Titulacion>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar titulación por ID",
            description = "Obtiene una titulación específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Titulación encontrada"),
            @ApiResponse(responseCode = "404", description = "No encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Titulacion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @Operation(
            summary = "Actualizar titulación",
            description = "Actualiza una titulación existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "No encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Titulacion> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Titulacion titulacion) {

        return ResponseEntity.ok(service.actualizar(id, titulacion));
    }

    @Operation(
            summary = "Eliminar titulación",
            description = "Elimina una titulación por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "No encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Titulación eliminada correctamente");
    }
}