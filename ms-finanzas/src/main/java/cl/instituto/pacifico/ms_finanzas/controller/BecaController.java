package cl.instituto.pacifico.ms_finanzas.controller;

import cl.instituto.pacifico.ms_finanzas.model.Beca;
import cl.instituto.pacifico.ms_finanzas.service.BecaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/becas")
@Tag(
        name = "Becas",
        description = "Operaciones relacionadas con la gestión de becas"
)
public class BecaController {

    private final BecaService service;

    public BecaController(BecaService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar becas",
            description = "Obtiene todas las becas registradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    @GetMapping
    public ResponseEntity<List<Beca>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar beca por ID",
            description = "Obtiene una beca según su identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Beca encontrada"),
            @ApiResponse(responseCode = "404", description = "Beca no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Beca> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @Operation(
            summary = "Crear beca",
            description = "Registra una nueva beca"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Beca creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Beca> guardar(
            @Valid @RequestBody Beca beca) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(beca));
    }

    @Operation(
            summary = "Actualizar beca",
            description = "Actualiza una beca existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Beca actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Beca no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Beca> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Beca beca) {

        return ResponseEntity.ok(
                service.actualizar(id, beca));
    }

    @Operation(
            summary = "Eliminar beca",
            description = "Elimina una beca por ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Beca eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Beca no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                "Beca eliminada correctamente");
    }
}