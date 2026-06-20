package cl.instituto.pacifico.ms_finanzas.controller;

import cl.instituto.pacifico.ms_finanzas.model.ConvenioPago;
import cl.instituto.pacifico.ms_finanzas.service.ConvenioPagoService;

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
@RequestMapping("/api/v1/convenios")
@Tag(
        name = "Convenios de Pago",
        description = "Operaciones relacionadas con la gestión de convenios de pago"
)
public class ConvenioPagoController {

    private final ConvenioPagoService service;

    public ConvenioPagoController(ConvenioPagoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar convenios",
            description = "Obtiene todos los convenios registrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    @GetMapping
    public ResponseEntity<List<ConvenioPago>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar convenio por ID",
            description = "Obtiene un convenio según su identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Convenio encontrado"),
            @ApiResponse(responseCode = "404", description = "Convenio no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ConvenioPago> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @Operation(
            summary = "Crear convenio",
            description = "Registra un nuevo convenio de pago"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Convenio creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<ConvenioPago> guardar(
            @Valid @RequestBody ConvenioPago convenioPago) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(convenioPago));
    }

    @Operation(
            summary = "Actualizar convenio",
            description = "Actualiza un convenio existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Convenio actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Convenio no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ConvenioPago> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ConvenioPago convenioPago) {

        return ResponseEntity.ok(
                service.actualizar(id, convenioPago));
    }

    @Operation(
            summary = "Eliminar convenio",
            description = "Elimina un convenio por ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Convenio eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Convenio no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                "Convenio eliminado correctamente");
    }
}