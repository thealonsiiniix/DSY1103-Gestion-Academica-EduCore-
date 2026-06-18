package cl.instituto.pacifico.ms_academico.controller;
import cl.instituto.pacifico.ms_academico.model.Carrera;
import cl.instituto.pacifico.ms_academico.service.CarreraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/academico")
@Tag(
        name = "Carreras",
        description = "Operaciones relacionadas con la gestión de carreras de la fundacion"
)
public class CarreraController {
    private final CarreraService service;
    public CarreraController(CarreraService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear carrera",
            description = "Registra una nueva carrera"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrera creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    })
    // CREAR
    @PostMapping
    public ResponseEntity<Carrera> crear(@RequestBody Carrera carrera) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(carrera));
    }

    @Operation(
            summary = "Listar carrera",
            description = "Obtiene todas las carreras registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    // LISTAR
    @GetMapping
    public ResponseEntity<List<Carrera>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar carrera por ID",
            description = "Obtiene una carrera específica según su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera encontrada"),
            @ApiResponse(responseCode = "404", description = "Carrera no encontrada")
    })
    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Actualizar carrera",
            description = "Actualiza los datos de una carrera existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera actualizada"),
            @ApiResponse(responseCode = "404", description = "Carrera no encontrada")
    })
    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> actualizar(@PathVariable Long id, @RequestBody Carrera carrera) {
        return ResponseEntity.ok(service.actualizar(id, carrera));
    }

    @Operation(
            summary = "Eliminar carrera",
            description = "Elimina una carrera existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera eliminada"),
            @ApiResponse(responseCode = "404", description = "Carrera no encontrada")
    })
    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Carrera eliminada");
    }

    @Operation(
            summary = "Buscar carrera por nombre",
            description = "Obtiene una carrera específica según su nombre"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera encontrada"),
            @ApiResponse(responseCode = "404", description = "Carrera no encontrada")
    })
    // BUSCAR POR NOMBRE
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Carrera>> buscarPorCarrera(@PathVariable String nombre) {
        return ResponseEntity.ok(service.buscarPorCarrera(nombre));
    }
}
