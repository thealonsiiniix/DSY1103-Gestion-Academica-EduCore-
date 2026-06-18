package cl.instituto.pacifico.ms_practicas.controller;

import cl.instituto.pacifico.ms_practicas.model.Practica;
import cl.instituto.pacifico.ms_practicas.service.PracticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/practica")
@Tag(
        name = "Practica",
        description = "Operaciones relacionadas con la gestión de practicas de estudiantes"
)
public class PracticaController {
    // inyyectamos a feign para utilizarlo
    private final PracticaService service;

    public PracticaController(PracticaService service){
        this.service = service;
    }

    @Operation(
            summary = "Crear practica",
            description = "Registra una practica validando estudiante"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Practica creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    })
    // CREAR PRACTICA - POST
    @PostMapping
    public ResponseEntity<Practica> crear(@RequestBody Practica practica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(practica));
    }

    @Operation(
            summary = "Listar practica",
            description = "Obtiene todas las practicas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    // LISTAR - GET
    @GetMapping
    public ResponseEntity<List<Practica>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar practica por ID",
            description = "Obtiene una practica específica según su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Practica encontrada"),
            @ApiResponse(responseCode = "404", description = "Practica no encontrada")
    })
    //  BUSCAR POR ID -GET
    @GetMapping("/{id}")
    public ResponseEntity<Practica> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar practica por rut estudiante",
            description = "Obtiene una practica específica según el rut del estudiante"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Practica encontrada"),
            @ApiResponse(responseCode = "404", description = "Practica no encontrada")
    })
    //  BUSCAR POR ESTUDIANTE - GET
    @GetMapping("/estudiante/{rutEstudiante}")
    public ResponseEntity<List<Practica>> obtenerPorRut(@PathVariable String rutEstudiante){
        return ResponseEntity.ok(service.obtenerPorRut(rutEstudiante));
    }

    @Operation(
            summary = "Eliminar practica",
            description = "Elimina una practica existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Practica eliminada"),
            @ApiResponse(responseCode = "404", description = "Practica no encontrada")
    })
    // ELIMINAR POR ID - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Practica Eliminada");
    }

    @Operation(
            summary = "Actualizar practica",
            description = "Actualiza los datos de una practica existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Practica actualizada"),
            @ApiResponse(responseCode = "404", description = "Practica no encontrada")
    })
    // PUT - ACTUALIZAR PRACTICA
    @PutMapping("/{id}")
    // ACTUALIZA LA PRACTICA SI EXISTE Y SI NO MUESTRA MENSAJE DE ERROR
    public ResponseEntity<Practica> actualizarCompleta(@PathVariable Long id, @RequestBody Practica practica) {
        return ResponseEntity.ok(service.actualizarCompleta(id, practica));
    }
}
