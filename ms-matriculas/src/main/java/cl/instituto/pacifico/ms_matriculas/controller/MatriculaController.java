package cl.instituto.pacifico.ms_matriculas.controller;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import cl.instituto.pacifico.ms_matriculas.service.MatriculaService;
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
@RequestMapping("/api/v1/matriculas")
@Tag(
        name = "Matrículas",
        description = "Operaciones relacionadas con la gestión de matrículas estudiantiles"
)
public class MatriculaController {
    private final MatriculaService service;
    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear matrícula",
            description = "Registra una nueva matrícula validando estudiante y carrera"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "422", description = "Regla de negocio incumplida")
    })
    @PostMapping
    public ResponseEntity<Matricula> crear(@Valid @RequestBody Matricula matricula) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(matricula));
    }

    @Operation(
            summary = "Listar matrículas",
            description = "Obtiene todas las matrículas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    @GetMapping
    public ResponseEntity<List<Matricula>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar matrícula por ID",
            description = "Obtiene una matrícula específica según su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @Operation(
            summary = "Actualizar matrícula",
            description = "Actualiza los datos de una matrícula existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula actualizada"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Matricula matricula) {

        return ResponseEntity.ok(service.actualizar(id, matricula));
    }

    @Operation(
            summary = "Eliminar matrícula",
            description = "Elimina una matrícula existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula eliminada"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Matrícula eliminada correctamente");
    }
}