package cl.instituto.pacifico.ms_estudiantes.controller;
import cl.instituto.pacifico.ms_estudiantes.model.Estudiante;
import cl.instituto.pacifico.ms_estudiantes.service.EstudianteService;
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
@RequestMapping("/api/v1/estudiantes")

@Tag(
        name = "Estudiantes",
        description = "Operaciones relacionadas con la gestión de estudiantes"
)
public class EstudianteController {
    private final EstudianteService service;
    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar estudiantes",
            description = "Obtiene todos los estudiantes registrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar estudiante por ID",
            description = "Obtiene un estudiante según su identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar estudiante por RUT",
            description = "Obtiene un estudiante según su RUT"
    )
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Estudiante> buscarPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(service.buscarPorRut(rut));
    }

    @Operation(
            summary = "Crear estudiante",
            description = "Registra un nuevo estudiante"
    )
    @PostMapping
    public ResponseEntity<Estudiante> guardar(@Valid @RequestBody Estudiante estudiante) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(estudiante));
    }

    @Operation(
            summary = "Actualizar estudiante",
            description = "Actualiza un estudiante existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(
                service.actualizar(id, estudiante));
    }

    @Operation(
            summary = "Eliminar estudiante",
            description = "Elimina un estudiante por ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(
                "Estudiante eliminado correctamente");
    }
}