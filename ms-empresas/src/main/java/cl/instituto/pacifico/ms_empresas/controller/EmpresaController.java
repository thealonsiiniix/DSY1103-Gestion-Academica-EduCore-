package cl.instituto.pacifico.ms_empresas.controller;

import cl.instituto.pacifico.ms_empresas.model.Empresa;
import cl.instituto.pacifico.ms_empresas.service.EmpresaService;
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
@RequestMapping("/api/v1/empresas")
@Tag(
        name = "Empresas",
        description = "Operaciones relacionadas con las empresas asociadas al Instituto Pacífico"
)
public class EmpresaController {
    private final EmpresaService service;
    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear empresa",
            description = "Permite registrar una nueva empresa en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Empresa> crear(
            @Valid @RequestBody Empresa empresa) {
        Empresa nuevaEmpresa = service.crear(empresa);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaEmpresa);
    }

    @Operation(
            summary = "Listar empresas",
            description = "Obtiene todas las empresas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<List<Empresa>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar empresa por ID",
            description = "Obtiene una empresa utilizando su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar empresa por RUT",
            description = "Obtiene una empresa utilizando su RUT"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @GetMapping("/buscar/rut/{rut}")
    public ResponseEntity<Empresa> buscarPorRut(
            @PathVariable String rut) {

        return ResponseEntity.ok(service.buscarPorRut(rut));
    }

    @Operation(
            summary = "Actualizar empresa",
            description = "Actualiza los datos de una empresa existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Empresa empresa) {

        return ResponseEntity.ok(
                service.actualizar(id, empresa)
        );
    }

    @Operation(
            summary = "Eliminar empresa",
            description = "Elimina una empresa registrada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                "Empresa eliminada correctamente"
        );
    }
}