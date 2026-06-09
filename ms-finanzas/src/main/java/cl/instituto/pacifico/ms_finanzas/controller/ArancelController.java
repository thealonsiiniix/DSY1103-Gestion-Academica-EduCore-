package cl.instituto.pacifico.ms_finanzas.controller;

import cl.instituto.pacifico.ms_finanzas.model.Arancel;
import cl.instituto.pacifico.ms_finanzas.service.ArancelService;

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
@RequestMapping("/api/v1/aranceles")

@Tag(
        name = "Aranceles",
        description = "Operaciones relacionadas con la gestión de aranceles"
)
public class ArancelController {

    private final ArancelService service;

    public ArancelController(ArancelService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar aranceles",
            description = "Obtiene todos los aranceles registrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    @GetMapping
    public ResponseEntity<List<Arancel>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar arancel por ID",
            description = "Obtiene un arancel según su identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arancel encontrado"),
            @ApiResponse(responseCode = "404", description = "Arancel no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Arancel> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar arancel por RUT",
            description = "Obtiene un arancel asociado a un estudiante por RUT"
    )
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Arancel> buscarPorRut(
            @PathVariable String rut) {

        return ResponseEntity.ok(
                service.buscarPorRut(rut));
    }

    @Operation(
            summary = "Crear arancel",
            description = "Registra un nuevo arancel"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Arancel creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Arancel> guardar(
            @Valid @RequestBody Arancel arancel) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(arancel));
    }

    @Operation(
            summary = "Actualizar arancel",
            description = "Actualiza un arancel existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Arancel> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Arancel arancel) {

        return ResponseEntity.ok(service.actualizar(id, arancel));
    }

    @Operation(
            summary = "Eliminar arancel",
            description = "Elimina un arancel por ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arancel eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Arancel no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Arancel eliminado correctamente");
    }
}